/**
 * @author Michael Zhou (Dominator008)
 */
package leveleditor;

import io.annotations.Decorator;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import core.characters.GameElement;

public class ClassTreeUtils {

    public static JTree getClassTree(String packageNames) {
	Map<Class<?>, DefaultMutableTreeNode> nodemap = new HashMap<Class<?>, DefaultMutableTreeNode>();
	Set<Class<?>> seen = new HashSet<Class<?>>();
	DefaultMutableTreeNode root = new DefaultMutableTreeNode(
		GameElement.class);
	nodemap.put(GameElement.class, root);
	JTree classtree = new ClassTree(root);
	Class<?>[] classes = getClassesInPackages(packageNames, null);
	boolean flag = false;
	while (!flag) {
	    flag = true;
	    for (Class<?> clazz : classes) {
		if (!seen.contains(clazz) && clazz.getSuperclass() != null
			&& nodemap.containsKey(clazz.getSuperclass())) {
		    flag = false;
		    seen.add(clazz);
		    DefaultMutableTreeNode parent = nodemap.get(clazz.getSuperclass());
		    DefaultMutableTreeNode child = new DefaultMutableTreeNode(clazz);
		    parent.add(child);
		    nodemap.put(clazz, child);
		}
	    }
	}
	return classtree;
    }
    
    public static Set<Class<?>> getDecorators(String packageNames, Class<?> target) {
	Class<?>[] classes = getClassesInPackages(packageNames, null);
	Set<Class<?>> decorators = new HashSet<Class<?>>();
	for (Class<?> clazz: classes) {
	    if (clazz.isAnnotationPresent(Decorator.class)) {
		Decorator decorator = clazz.getAnnotation(Decorator.class);
		if (!decorator.target().equals(target)) continue;
		decorators.add(clazz);
	    }
	}
	return decorators;
    }

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages. Adapted from
     * http://snippets.dzone.com/posts/show/4831 and extended to support use of
     * JAR files
     * 
     * @param packageNames
     *            The base packages, separated by comma
     * @param regexFilter
     *            an optional class name pattern.
     * @return The classes
     */
    public static Class<?>[] getClassesInPackages(String packageNames,
	    String regexFilter) {
	Pattern regex = null;
	if (regexFilter != null)
	    regex = Pattern.compile(regexFilter);
	try {
	    ClassLoader classLoader = Thread.currentThread()
		    .getContextClassLoader();
	    assert classLoader != null;
	    String[] paths = packageNames.split(",");
	    ArrayList<Class<?>> classList = new ArrayList<Class<?>>();
	    TreeSet<String> classes = new TreeSet<String>();
	    for (String path: paths) {
		    String onepackageName = path.replace('.', '/').trim();
		    Enumeration<URL> resources = classLoader.getResources(onepackageName);
		    List<String> dirs = new ArrayList<String>();
		    while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(resource.getFile());
		    }
		    for (String directory : dirs) {
			classes.addAll(findClasses(directory, onepackageName, regex));
		    }
		    for (String clazz : classes) {
			classList.add(Class.forName(clazz));
		    }
	    }
	    return classList.toArray(new Class[classes.size()]);
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * Recursive method used to find all classes in a given path (directory or
     * zip file url). Directories are searched recursively.
     * Adapted from http://snippets.dzone.com/posts/show/4831 and extended to
     * support use of JAR files
     * 
     * @param path
     *            The base directory or url from which to search.
     * @param packageName
     *            The package name for classes found inside the base directory
     * @param regex
     *            an optional class name pattern. e.g. .*Test
     * @return The classes
     */
    private static TreeSet<String> findClasses(String path, String packageName,
	    Pattern regex) throws Exception {
	TreeSet<String> classes = new TreeSet<String>();
	if (path.startsWith("file:") && path.contains("!")) {
	    String[] split = path.split("!");
	    URL jar = new URL(split[0]);
	    ZipInputStream zip = new ZipInputStream(jar.openStream());
	    ZipEntry entry;
	    while ((entry = zip.getNextEntry()) != null) {
		if (entry.getName().endsWith(".class")) {
		    String className = entry.getName().replaceAll("[$].*", "")
			    .replaceAll("[.]class", "").replace('/', '.');
		    if (className.startsWith(packageName)
			    && (regex == null || regex.matcher(className)
				    .matches()))
			classes.add(className);
		}
	    }
	}
	File dir = new File(path);
	if (!dir.exists()) {
	    return classes;
	}
	File[] files = dir.listFiles();
	for (File file : files) {
	    if (file.isDirectory()) {
		assert !file.getName().contains(".");
		classes.addAll(findClasses(file.getAbsolutePath(), packageName
			+ "." + file.getName(), regex));
	    } else if (file.getName().endsWith(".class")) {
		String className = packageName
			+ '.'
			+ file.getName().substring(0,
				file.getName().length() - 6);
		if (regex == null || regex.matcher(className).matches())
		    classes.add(className);
	    }
	}
	return classes;
    }

}