# UDF_reference

To use the reference udf:

1) Create a folder containing the jar file in the unix environment;

2) ADD JAR path/reference.jar;

3) ADD FILE reference_file.txt;

4) LIST JARS; //verify the jar

5) LIST FILES; //verify the reference file

6) CREATE TEMPORARY FUNCTION reference AS 'pkg.UDFReference'; 

Then use the udf inside hive passing the file added to the hadoop distributed cache as second argument.

