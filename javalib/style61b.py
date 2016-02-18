import sys
import os

classpath = os.environ['CLASSPATH']

if len(sys.argv) < 2:
	print("Usage:\npython style61b.py *.java")
	print("Any files in ./styleskip.txt will be ignored.")
	sys.exit()

unix_dir_names = classpath.split(':')
windows_dir_names = classpath.split(';')

all_dirs = unix_dir_names
all_dirs.extend(windows_dir_names)

checkstyle_jar_name = 'checkstyle-6.15-all.jar'
checkstyle_rules_name = '61b_checks.xml'

checkstyle_jar_path = None
checkstyle_rules_path = None

for dirname in all_dirs:
	#strip wildcard, if any
	stripped_dirname = dirname.strip('*')
	candidate_jar_path = os.path.join(stripped_dirname, checkstyle_jar_name)
	if (os.path.isfile(candidate_jar_path)):
		checkstyle_jar_path = candidate_jar_path

	candidate_rules_path = os.path.join(stripped_dirname, checkstyle_rules_name)
	if (os.path.isfile(candidate_rules_path)):
		checkstyle_rules_path = candidate_rules_path


if checkstyle_jar_path == None:
	print("Could not find " + checkstyle_jar_name + " in your classpath!")
	sys.exit()

if checkstyle_rules_path == None:
	print("Could not find " + checkstyle_rules_name + " in your classpath!")
	sys.exit()

skipfiles = set([])
if os.path.isfile('styleskip.txt'):
	with open("styleskip.txt", "r") as f:
		for line in f:
			skipfiles.add(line.strip())
else:
	print("No styleskip.txt found. Create one if you'd like to skip some files.")

files_to_check = set(sys.argv[1:]).difference(skipfiles)

system_command = 'java -jar "' + checkstyle_jar_path +  '" -c "' + checkstyle_rules_path + '" ' + " ".join(files_to_check)
print("Running command: " + system_command)
os.system(system_command)
