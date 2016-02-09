## This quick and dirty script attempts to diagnose your classpath. I think it might work on Windows even.

import os
import sys

if 'CLASSPATH' not in os.environ:
	print('Your classpath environment variable is not set. See the directions. If you have already seen them, try starting a new terminal window. If that still doesn''t work, please post to Piazza, providing detailed information about what you tried. Include your OS. Screenshots would be nice.')
	sys.exit()

classpath = os.environ['CLASSPATH']

print("Your classpath is: " + classpath + "\n")

if '~' in classpath:
	print("Your classpath contains a ~. It should not. Replace this character with the full path to the folder in question.")

if '*.jar' in classpath:
	print("Your classpath contains *.jar. Remove the '.jar' part.")

if '*' not in classpath:
	print("Your classpath does not contain a * character. This indicates something is wrong. Check the directions again.")

print("This means that when you run Java from the command line, it will look in the *ed folders for java libraries (which have a .jar extension). This program will now inspect all of those folders.\n")

jarfile = 'junit-4.12.jar'
print('Looking for ' + jarfile + ' in all directories that are *ed.')


#os.pathsep is ; in windows, : in Linux
folders = classpath.split(os.pathsep) 
found = False

for folder in folders:	
	if len(folder) == 0:
		continue

	if folder[-1] == '*':
		folder = folder[:-1]
		print('\n==> Inspecting: ' + folder)

		if os.path.isfile(os.path.join(folder, '*')):
			print("\tWARNING: The folder contains a file named *\n\tPlease remove this file.")
			sys.exit()

		if os.path.isdir(folder):			
			files = os.listdir(folder)
			if jarfile not in files:
				print("         " + jarfile + " not found.")
			else:
				print("         " + jarfile + " found.")
				found = True
				break
print("\n")

if found:
	print("Your classpath appears to be correct. If your JUnit tests are still not compiling, please post to Piazza. Provide detailed information. Screenshots would be nice.")
else:
	print("None of the *ed folders in your classpath contain " + jarfile + ". See the directions. If you're stuck, please post to Piazza. Provide detailed information. Screenshots would be nice.")