sqlgenerator
============

SQL statement generator using Java


Following example generates SQL statements.

Entity class - this represents a table in the database.

Field class - this represents a field in a table. Each field belongs to an Entity.

Join class - this represents the join between any two tables.

EntityLookup class - This class will instantiate, in its constructor, a set of Entity, Field and Join classes that represent the SQL tables and fields.

SQLGenerator class - This class generates standart SQL given list of Fields, Entities and Joins.

SQLBuilderTest class - This is a Junit test class to generate SQL statements and test various test cases.
