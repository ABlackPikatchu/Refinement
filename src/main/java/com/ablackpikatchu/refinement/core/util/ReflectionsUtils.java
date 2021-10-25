package com.ablackpikatchu.refinement.core.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.reflect.ClassPath;

@SuppressWarnings("rawtypes")
public class ReflectionsUtils {

	private ReflectionsUtils() {
	}

	public static Set<Class<?>> findAllClassesUsingGoogleGuice(String packageName) {
		try {
			return ClassPath.from(ClassLoader.getSystemClassLoader()).getAllClasses().stream()
					.filter(clazz -> clazz.getPackageName().startsWith(packageName))
					.map(clazz -> {
						try {
							return Class.forName(clazz.getName(), false, ClassLoader.getSystemClassLoader());
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						return null;
					})
					.collect(Collectors.toSet());
		} catch (IOException e) {
		}
		return Collections.emptySet();
	}

	public static Set<Class<?>> filterClassesAnnotatedWith(Set<Class<?>> allClasses,
			Class<? extends Annotation> annotation) {
		return allClasses.stream().filter(clazz -> clazz.isAnnotationPresent(annotation)).collect(Collectors.toSet());
	}

	/**
	 * Scans all classes accessible from the context class loader which belong to
	 * the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class[] getClasses(String packageName) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			assert classLoader != null;
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			ArrayList<Class> classes = new ArrayList<Class>();
			for (File directory : dirs) {
				classes.addAll(findClasses(directory, packageName));
			}
			return classes.toArray(new Class[classes.size()]);
		} catch (ClassNotFoundException | IOException e) {

		}
		return new Class[] {};
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base
	 *                    directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	public static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6),
						false, Thread.currentThread().getContextClassLoader()));
			}
		}
		return classes;
	}

	public static ArrayList<Field> getFieldsAnnotatedWith(ArrayList<Class<?>> classes,
			Class<? extends Annotation> annotation) {
		ArrayList<Field> fields = new ArrayList<>();
		classes.forEach(clazz -> {
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(annotation))
					fields.add(field);
			}
		});
		return fields;
	}

	/**
	 * Private helper method
	 * 
	 * @param directory The directory to start with
	 * @param pckgname  The package name to search for. Will be needed for getting
	 *                  the Class object.
	 * @param classes   if a file isn't loaded but still is in the directory
	 * @throws ClassNotFoundException
	 */
	private static void checkDirectory(File directory, String pckgname, ArrayList<Class<?>> classes)
			throws ClassNotFoundException {
		File tmpDirectory;

		if (directory.exists() && directory.isDirectory()) {
			final String[] files = directory.list();

			for (final String file : files) {
				if (file.endsWith(".class")) {
					try {
						classes.add(Class.forName(pckgname + '.' + file.substring(0, file.length() - 6), false,
								Thread.currentThread().getContextClassLoader()));
					} catch (final NoClassDefFoundError e) {
						// do nothing. this class hasn't been found by the
						// loader, and we don't care.
					}
				} else if ((tmpDirectory = new File(directory, file)).isDirectory()) {
					checkDirectory(tmpDirectory, pckgname + "." + file, classes);
				}
			}
		}
	}

	/**
	 * Private helper method.
	 * 
	 * @param connection the connection to the jar
	 * @param pckgname   the package name to search for
	 * @param classes    the current ArrayList of all classes. This method will
	 *                   simply add new classes.
	 * @throws ClassNotFoundException if a file isn't loaded but still is in the jar
	 *                                file
	 * @throws IOException            if it can't correctly read from the jar file.
	 */
	private static void checkJarFile(JarURLConnection connection, String pckgname, ArrayList<Class<?>> classes)
			throws ClassNotFoundException, IOException {
		final JarFile jarFile = connection.getJarFile();
		final Enumeration<JarEntry> entries = jarFile.entries();
		String name;

		for (JarEntry jarEntry = null; entries.hasMoreElements() && ((jarEntry = entries.nextElement()) != null);) {
			name = jarEntry.getName();

			if (name.contains(".class")) {
				name = name.substring(0, name.length() - 6).replace('/', '.');

				if (name.contains(pckgname)) {
					classes.add(Class.forName(name, false, Thread.currentThread().getContextClassLoader()));
				}
			}
		}
	}

	/**
	 * Attempts to list all the classes in the specified package as determined by
	 * the context class loader
	 * 
	 * @param pckgname the package name to search
	 * @return a list of classes that exist within that package
	 * @throws ClassNotFoundException if something went wrong
	 */
	public static ArrayList<Class<?>> getClassesForPackage(String pckgname) throws ClassNotFoundException {
		final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		try {
			final ClassLoader cld = Thread.currentThread().getContextClassLoader();

			if (cld == null)
				throw new ClassNotFoundException("Can't get class loader.");

			final Enumeration<URL> resources = cld.getResources(pckgname.replace('.', '/'));
			URLConnection connection;

			for (URL url = null; resources.hasMoreElements() && ((url = resources.nextElement()) != null);) {
				try {
					connection = url.openConnection();

					if (connection instanceof JarURLConnection) {
						checkJarFile((JarURLConnection) connection, pckgname, classes);
					} else {
						try {
							checkDirectory(new File(URLDecoder.decode(url.getPath(), "UTF-8")), pckgname, classes);
						} catch (final UnsupportedEncodingException ex) {
							throw new ClassNotFoundException(
									pckgname + " does not appear to be a valid package (Unsupported encoding)", ex);
						}
					}
				} catch (final IOException ioex) {
					throw new ClassNotFoundException(
							"IOException was thrown when trying to get all resources for " + pckgname, ioex);
				}
			}
		} catch (final NullPointerException ex) {
			throw new ClassNotFoundException(
					pckgname + " does not appear to be a valid package (Null pointer exception)", ex);
		} catch (final IOException ioex) {
			throw new ClassNotFoundException("IOException was thrown when trying to get all resources for " + pckgname,
					ioex);
		}

		return classes;
	}
	
	public static ArrayList<Class<?>> getClassesForPkg(final String pkgName) throws IOException, URISyntaxException {
	    final String pkgPath = pkgName.replace('.', '/');
	    final URI pkg = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(pkgPath)).toURI();
	    final ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();

	    Path root;
	    if (pkg.toString().startsWith("jar:")) {
	        try {
	            root = FileSystems.getFileSystem(pkg).getPath(pkgPath);
	        } catch (final FileSystemNotFoundException e) {
	            root = FileSystems.newFileSystem(pkg, Collections.emptyMap()).getPath(pkgPath);
	        }
	    } else {
	        root = Paths.get(pkg);
	    }

	    final String extension = ".class";
	    try (final Stream<Path> allPaths = Files.walk(root)) {
	        allPaths.filter(Files::isRegularFile).forEach(file -> {
	            try {
	                final String path = file.toString().replace('\\', '.');
	                final String name = path.substring(path.indexOf(pkgName), path.length() - extension.length());
	                allClasses.add(Class.forName(name, false, ClassLoader.getSystemClassLoader()));
	            } catch (final ClassNotFoundException | StringIndexOutOfBoundsException ignored) {
	            }
	        });
	    }
	    return allClasses;
	}
	
	public static class ClassEnumerator {
		
		private static final String CLASS_SUFFIX = ".class";

		private static Class<?> loadClass(String cls) {
			try {
				return Class.forName(cls, false, ClassLoader.getSystemClassLoader());
			} 
			catch (ClassNotFoundException e) {
				String err = "Unexpected ClassNotFoundException loading class [%s]";
				throw new ClassEnumException(String.format(err, cls), e);
			}
		}
		
		/**
		 * Given a package name and a directory returns all classes within that
		 * directory
		 * 
		 * @param dir
		 * @param pkgname
		 * @return Classes within Directory with package name
		 */
		public static List<Class<?>> processDirectory(File dir, String pkgname) {
			List<Class<?>> classes = new ArrayList<>();
			for(String file : dir.list()) {
				String cls = null;
				// we are only interested in .class files
				if (file.endsWith(CLASS_SUFFIX)) {
					// removes the .class extension
					cls = pkgname + '.' + file.substring(0, file.length() - 6);
					classes.add(loadClass(cls));
				}
				// If the file is a directory recursively class this method.
				File subdir = new File(dir, file);
				if (subdir.isDirectory()) {
					classes.addAll(processDirectory(subdir, pkgname + '.' + file));
				}
			}
			return classes;
		}

		/**
		 * Given a jar file's URL and a package name returns all classes within jar
		 * file.
		 * 
		 * @param resource
		 * @param pkgname
		 */
		public static List<Class<?>> processJarfile(URL resource, String pkgname) {
			List<Class<?>> classes = new ArrayList<>();
			// Turn package name to relative path to jar file
			String relPath = pkgname.replace('.', '/');
			String resPath = resource.getPath();
			String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");

			try (JarFile jarFile = new JarFile(jarPath)) {
				// attempt to load jar file

				// get contents of jar file and iterate through them
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();

					// Get content name from jar file
					String entryName = entry.getName();
					String className = null;

					// If content is a class save class name.
					if (entryName.endsWith(CLASS_SUFFIX) && entryName.startsWith(relPath)
							&& entryName.length() > (relPath.length() + "/".length())) {
						className = entryName.replace('/', '.').replace('\\', '.').replace(CLASS_SUFFIX, "");
					}

					// If content is a class add class to List
					if (className != null) {
						classes.add(loadClass(className));
					}
				}
			} catch (IOException e) {
				String err = "Unexpected IOException reading JAR File [%s]";
				throw new ClassEnumException(String.format(err, jarPath), e);
			}
			return classes;
		}
		/**
		 * Give a package this method returns all classes contained in that package
		 * @param pkg
		 */
		public static List<Class<?>> getPackageClasses(Package pkg) {
			return getPackageClasses(pkg, ClassLoader.getSystemClassLoader());
		}
		
		/**
		 * Given a package name and class loader this method returns all classes
		 * contained in package.
		 * 
		 * @param p
		 * @param l
		 * @return
		 * @throws IOException
		 */
		public static List<Class<?>> getPackageClasses(Package p, ClassLoader l) {
			List<Class<?>> classes = new ArrayList<>();

			// Get name of package and turn it to a relative path
			String pkgname = p.getName();
			String relPath = pkgname.replace('.', '/');

			// Get a File object for the package
			try {
				Enumeration<URL> resources = l.getResources(relPath);
				if (!resources.hasMoreElements()) {
					String err = "Unexpected problem: No resource for {%s}";
					throw new ClassEnumException(String.format(err, relPath));
				} else {
					do {
						URL resource = resources.nextElement();
						// If the resource is a jar get all classes from jar
						if (resource.toString().startsWith("jar:")) {
							classes.addAll(processJarfile(resource, pkgname));
						} else {
							File dir = new File(resource.getPath());
							classes.addAll(processDirectory(dir, pkgname));
						}
					} while (resources.hasMoreElements());
				}
				return classes;
			} catch (IOException e) {
				String err = "Unexpected error loading resources";
				throw new ClassEnumException(err, e);
			}
		}
		
		private ClassEnumerator() {
			
		}
		

		public static class ClassEnumException extends RuntimeException {

			private static final long serialVersionUID = 909384213793458361L;

			public ClassEnumException() {
				super();
			}

			public ClassEnumException(String message) {
				super(message);
			}

			public ClassEnumException(String message, Throwable cause) {
				super(message, cause);
			}

			public ClassEnumException(Throwable cause) {
				super(cause);
			}
		}
	}
}
