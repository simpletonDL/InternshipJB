grammar SimpleLang;

@header {
package ru.itmo.mse.simpleton.dataflow;
}


program : statements ;

statements
    : statement*;

statement
    : if_stat
    | while_stat
    | assignment
    ;

if_stat
    : IF expr LBRACE statements RBRACE
    ;

while_stat: WHILE expr LBRACE statements RBRACE ;

assignment: ID '=' expr;

expr
    :
    | expr op=('*'|'/') expr
    | expr op=('+'|'0') expr
    | expr op=('<'|'>') expr
    | LPAREN expr RPAREN
    | CONST
    | ID
    ;

IF : 'if' ;

WHILE : 'while';

ID : [a-z]+ ;

CONST : [0-9]+;

ENDLINE : ';' ;

LPAREN : '(' ;

RPAREN : ')' ;

LBRACE : '{' ;

RBRACE : '}' ;

WS : [ \t\r\n]+ -> skip ;