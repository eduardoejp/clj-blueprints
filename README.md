
clj-blueprints
==============

`clj-blueprints` is a binding for the Blueprints graph DBMS API.

Usage
-----

Simply add this to your leiningen deps: `[clj-blueprints "0.1.0"]`

Documentation
-------------

The documentation can be found here: http://eduardoejp.github.com/clj-blueprints/

Examples
--------

Working with the database:

	; Opening the database and setting the *db* var for global use.
	(set-db! (tinker-graph)) ; You can open every other GraphDB by instantiation the required GraphDB object (like OrientGraph)

	; Dynamically bind *db* to another DB.
	(with-db (tinker-graph)
	  (form-1 ...)
	  (form-2 ...)
	  (form-3 ...)
	  ...
	  (form-n ...))

	; Shutdown the DB
	(shutdown!)

	; Using transactions (*db* must be bound to some database in the surrounding scope).
	(with-tx
	  (form-1 ...)
	  (form-2 ...)
	  (form-3 ...)
	  ...
	  (form-n ...))

Working with vertices and eges:

	(with-tx
	  (let [v1 (vertex {:first-name "John", :last-name "Doe", :age 20, :country "USA"})
	        v2 (vertex {:first-name "Jane", :last-name "Doe", :age 25, :country "USA"})]
	    (link! v1 :knows {:since "2012/02/24"} v2)))

*Please note*: This is not a comprehensive guide. Please read the library documentation to know what functions and macros are available.

