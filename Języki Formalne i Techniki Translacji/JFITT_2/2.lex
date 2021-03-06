%x COMMENT
%%	
"//".*		/* Ignore one-line comments */
"//"(.*\\\n)*.*	/*Ignore "multi-line one-line comments" */


"/*"	BEGIN(COMMENT);
<COMMENT>[^*\n]*	//Wszystko, co nie jest "*" lub \n
<COMMENT>"*"+[^*/\n]*	//Samotne gwiazdki
<COMMENT>"*"+"/"	BEGIN(INITIAL);

\".*"/*".*"*/".*\"	fprintf(yyout, "%s", yytext);
.			fprintf(yyout, "%s", yytext);
%%

main(int argc, char** argv ) {
	       ++argv, --argc;	/* skip over program name */
	       if ( argc > 0 )
		       yyin = fopen( argv[0], "r+" );
	       else {
		       yyin = stdin;
		}
		yyout = fopen("temporary", "w+");
	       yylex();	
			
	       }
