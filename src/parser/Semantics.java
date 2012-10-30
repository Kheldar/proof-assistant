//=========================================================================
//
//  This skeleton was generated by Mouse 1.5 at 2012-10-28 08:40:29 GMT
//  from grammar
//    'C:\Users\Silk\workspace\proof-assistant\src\parser\grammar.txt'.
//
//=========================================================================

package parser;

import java.util.HashSet;

import syntax.And;
import syntax.BinaryConnective;
import syntax.False;
import syntax.Formula;
import syntax.Implies;
import syntax.Not;
import syntax.Or;
import syntax.Predicate;

class Semantics extends mouse.runtime.SemanticsBase
{
	
	HashSet<Predicate> symbolTable = new HashSet<Predicate>();
  //-------------------------------------------------------------------
  //  Formula = Predicate
  //-------------------------------------------------------------------
  void moveUpPred()
    {lhs().put(rhs(0).get());}
  
  //-------------------------------------------------------------------
  //  Formula = False
  //-------------------------------------------------------------------
  void fls()
    {lhs().put(new False());}
  
  //-------------------------------------------------------------------
  //  Formula = "(" Formula Space BinaryCon Space Formula ")"
  //-------------------------------------------------------------------
  void binaryFormula()
    {
	  try {
		  Class<? extends BinaryConnective> c = (Class<? extends BinaryConnective>) rhs(3).get();

		  Formula f = c.getConstructor(Formula.class, Formula.class).newInstance(
				  (Formula) rhs(1).get(),
				  (Formula) rhs(5).get());
		  lhs().put(f);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
    }
  
  //-------------------------------------------------------------------
  //  Formula = Not Formula
  //-------------------------------------------------------------------
  void notFormula()
    {lhs().put(new Not((Formula) rhs(1).get()));}
  
  //-------------------------------------------------------------------
  //  BinaryCon = And
  //-------------------------------------------------------------------
  void and()
    {lhs().put(And.class);}
  
  //-------------------------------------------------------------------
  //  BinaryCon = Or
  //-------------------------------------------------------------------
  void or()
    {lhs().put(Or.class);}
  
  //-------------------------------------------------------------------
  //  BinaryCon = Implies
  //-------------------------------------------------------------------
  void implies()
    {lhs().put(Implies.class);}
  
  //-------------------------------------------------------------------
  //  Predicate = letter [0-9]*
  //-------------------------------------------------------------------
  void predicate()
    {
	  Predicate p = new Predicate(lhs().text());
	  if(symbolTable.contains(p)) {
		  for(Predicate symbol : symbolTable) {
			  if(p.equals(symbol)) {
				  p = symbol;
				  break;
			  }
		  }
	  } else {
		  symbolTable.add(p);
	  }
	  lhs().put(p);
    }
  
}