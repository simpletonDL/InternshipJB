// Generated from SimpleLang.g4 by ANTLR 4.7.2

package ru.itmo.mse.simpleton.dataflow;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLangParser}.
 */
public interface SimpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SimpleLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SimpleLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(SimpleLangParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(SimpleLangParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SimpleLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SimpleLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_stat(SimpleLangParser.If_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_stat(SimpleLangParser.If_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stat(SimpleLangParser.While_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stat(SimpleLangParser.While_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SimpleLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SimpleLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(SimpleLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(SimpleLangParser.ExprContext ctx);
}