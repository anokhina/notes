see http://plantuml.com/command-line
http://plantuml.com/class-diagram

Для работы необходим [plantuml.jar](plantuml.jar)

Документация [PlantUML_Language_Reference_Guide.pdf](PlantUML_Language_Reference_Guide.pdf) и [PlantUML_Language_Reference_Guide_RU.pdf](PlantUML_Language_Reference_Guide_RU.pdf)

```shell
sudo apt install python-pydot python-pydot-ng graphviz
```

## Command line

You can also run PlantUML using the following command :

```shell
java -jar plantuml.jar file1 file2 file3
```

This will look for @startXXX into file1, file2 and file3. For each diagram, a .png file will be created.

For processing a whole directory, you can use:

```shell
java -jar plantuml.jar "c:/directory1" "c:/directory2"
```

This command will search for @startXXX and @endXXX into .c, .h, .cpp, .txt, .pu, .tex, .html, .htm or .java files of the c:/directory1 and c:/directory2 directories.


## Wildcards

You can also use wildcards :

```
* For a single character, use ?
* For zero or more characters, use *
* For zero or more characters, (including / or \), use a double **
* So to process any .cpp files in all directories starting by dummy :
```

```shell
java -jar plantuml.jar "dummy*/*.cpp"
```

And to process any .cpp files in all directories starting by dummy, and theirs subdirectories :

```shell
java -jar plantuml.jar "dummy*/**.cpp"
```

## Excluded files

You can exlude some files from the process using the -x option:

```shell
java -jar plantuml.jar -x "**/common/**" -x "**/test/Test*" "dummy*/**/*.cpp" 
```

## Output Directory

You can specify an output directory for all images using the -o switch:

```shell
java -jar plantuml.jar -o "c:/outputPng" "c:/directory2"
```

If you recurse into several directory, there is a slight difference if you provide an absolute or a relative path for this output directory.

```
absolute path: An unique output directory will be used, with all images generated inside
relative path: Several output directories will be used across the file tree.
```

## Configuration file

You can also provide a configuration file which will be included before each diagram:

```shell
java -jar plantuml.jar -config "./config.cfg" dir1
```

## Exit code

When there are some errors in diagrams the command returns an error (-1) exit code. But even if some diagrams contain some errors, all diagrams are generated, which can be time consuming for large project.

You can use the -failfast flag to change this behavior to stop diagram generations as soon as one error occurs. In that case, some diagrams will be generated, and some will not.

There is also a -failfast2 flag that does a first checking pass. If some error is present, no diagram will be generated at all. In case of error, -failfast2 runs even faster than -failfast, which may be useful for huge project.

## Standard Input&Ouput

Using the -pipe option, you can easily use PlantUML into your scripts.

With this option, diagram description is received through the standard input and the png file is generated to the standard output. No file are written on the local file system.

Example:

```shell
type somefile.txt | java -jar plantuml.jar -pipe > somefile.png
```

## Help

You can have a help message by launching :

```shell
java -jar plantuml.jar -help
```

This will output:

```
Usage: java -jar plantuml.jar [options] -gui
	(to execute the GUI)
	or java -jar plantuml.jar [options] [file/dir] [file/dir] [file/dir]
	(to process files or directories)

You can use the following wildcards in files/dirs:
	*	means any characters but '\'
	?	one and only one character but '\'
	**	means any characters (used to recurse through directories)

where options include:
	-gui			To run the graphical user interface
	-tpng			To generate images using PNG format (default)
	-tsvg			To generate images using SVG format
	-teps			To generate images using EPS format
	-tpdf			To generate images using PDF format
	-tvdx			To generate images using VDX format
	-txmi			To generate XMI file for class diagram
	-tscxml			To generate SCXML file for state diagram
	-thtml			To generate HTML file for class diagram
	-ttxt			To generate images with ASCII art
	-tutxt			To generate images with ASCII art using Unicode characters
	-tlatex			To generate images using LaTeX/Tikz format
	-tlatex:nopreamble		To generate images using LaTeX/Tikz format without preamble
	-o[utput] "dir"		To generate images in the specified directory
	-DVAR1=value		To set a preprocessing variable as if '!define VAR1 value' were used
	-Sparam1=value		To set a skin parameter as if 'skinparam param1 value' were used
	-r[ecurse]			recurse through directories
	-I/path/to/file		To include file as if '!include file' were used
	-I/path/to/*.puml		To include files with pattern
	-charset xxx		To use a specific charset (default is windows-1252)
	-e[x]clude pattern		To exclude files that match the provided pattern
	-metadata			To retrieve PlantUML sources from PNG images
	-checkmetadata		Skip PNG files that don't need to be regenerated
	-version			To display information about PlantUML and Java versions
	-checkversion		To check if a newer version is available for download
	-v[erbose]			To have log information
	-quiet			To NOT print error message into the console
	-keepfiles			To NOT delete temporary files after process
	-h[elp]			To display this help message
	-testdot			To test the installation of graphviz
	-graphvizdot "exe"		To specify dot executable
	-p[ipe]			To use stdin for PlantUML source and stdout for PNG/SVG/EPS generation
	-encodesprite 4|8|16[z] "file"	To encode a sprite at gray level (z for compression) from an image
	-computeurl|-encodeurl		To compute the encoded URL of a PlantUML source file
	-decodeurl			To retrieve the PlantUML source from an encoded URL
	-syntax			To report any syntax error from standard input without generating images
	-language			To print the list of PlantUML keywords
	-nosuggestengine		To disable the suggest engine when errors in diagrams
	-checkonly			To check the syntax of files without generating images
	-failfast			To stop processing as soon as a syntax error in diagram occurs
	-failfast2			To do a first syntax check before processing files, to fail even faster
	-pattern			To print the list of Regular Expression used by PlantUML
	-duration			To print the duration of complete diagrams processing
	-nbthread N			To use (N) threads for processing
	-nbthread auto		To use 4 threads for processing
	-author[s]			To print information about PlantUML authors
	-overwrite			To allow to overwrite read only files
	-printfonts			To print fonts available on your system
	-enablestats		To enable statistics computation
	-disablestats		To disable statistics computation (default)
	-htmlstats			To output general statistics in file plantuml-stats.html
	-xmlstats			To output general statistics in file plantuml-stats.xml
	-realtimestats		To generate statistics on the fly rather than at the end
	-loopstats			To continuously print statistics about usage
	-splash			To display a splash screen with some progress bar
	-progress			To display a textual progress bar in console
	-pipeimageindex N		To generate the Nth image with pipe option
	-stdlib			To print standart library info
	-extractstdlib		To extract PlantUML Standard Library into stdlib folder
	-filename \"example.puml\"	To override %filename% variable
	-preproc			To output preprocessor text of diagrams
	-cypher			To cypher texts of diagrams so that you can share them
```

If needed, you can setup the environment variable GRAPHVIZ_DOT
