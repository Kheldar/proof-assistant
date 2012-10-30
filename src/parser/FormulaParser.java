//=========================================================================
//
//  This file was generated by Mouse 1.5 at 2012-10-28 08:40:29 GMT
//  from grammar
//    'C:\Users\Silk\workspace\proof-assistant\src\parser\grammar.txt'.
//
//=========================================================================

package parser;

import mouse.runtime.Source;

public class FormulaParser extends mouse.runtime.ParserBase
{
  final Semantics sem;
  
  //=======================================================================
  //
  //  Initialization
  //
  //=======================================================================
  //-------------------------------------------------------------------
  //  Constructor
  //-------------------------------------------------------------------
  public FormulaParser()
    {
      sem = new Semantics();
      sem.rule = this;
      super.sem = sem;
    }
  
  //-------------------------------------------------------------------
  //  Run the parser
  //-------------------------------------------------------------------
  public boolean parse(Source src)
    {
      super.init(src);
      sem.init();
      if (Formula()) return true;
      return failure();
    }
  
  //-------------------------------------------------------------------
  //  Get semantics
  //-------------------------------------------------------------------
  public Semantics semantics()
    { return sem; }
  
  //=======================================================================
  //
  //  Parsing procedures
  //
  //=======================================================================
  //=====================================================================
  //  Formula = Predicate {moveUpPred} / False {fls} / "(" Formula Space
  //    BinaryCon Space Formula ")" {binaryFormula} / Not Formula
  //    {notFormula} ;
  //=====================================================================
  private boolean Formula()
    {
      begin("Formula");
      if (Predicate())
      { sem.moveUpPred(); return accept(); }
      if (False())
      { sem.fls(); return accept(); }
      if (Formula_0())
      { sem.binaryFormula(); return accept(); }
      if (Formula_1())
      { sem.notFormula(); return accept(); }
      return reject();
    }
  
  //-------------------------------------------------------------------
  //  Formula_0 = "(" Formula Space BinaryCon Space Formula ")"
  //-------------------------------------------------------------------
  private boolean Formula_0()
    {
      begin("");
      if (!next('(')) return rejectInner();
      if (!Formula()) return rejectInner();
      if (!Space()) return rejectInner();
      if (!BinaryCon()) return rejectInner();
      if (!Space()) return rejectInner();
      if (!Formula()) return rejectInner();
      if (!next(')')) return rejectInner();
      return acceptInner();
    }
  
  //-------------------------------------------------------------------
  //  Formula_1 = Not Formula
  //-------------------------------------------------------------------
  private boolean Formula_1()
    {
      begin("");
      if (!Not()) return rejectInner();
      if (!Formula()) return rejectInner();
      return acceptInner();
    }
  
  //=====================================================================
  //  BinaryCon = And {and} / Or {or} / Implies {implies} ;
  //=====================================================================
  private boolean BinaryCon()
    {
      begin("BinaryCon");
      if (And())
      { sem.and(); return accept(); }
      if (Or())
      { sem.or(); return accept(); }
      if (Implies())
      { sem.implies(); return accept(); }
      return reject();
    }
  
  //=====================================================================
  //  And = "&" ;
  //=====================================================================
  private boolean And()
    {
      begin("And");
      if (!next('&')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  Or = "|" ;
  //=====================================================================
  private boolean Or()
    {
      begin("Or");
      if (!next('|')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  Implies = ">" ;
  //=====================================================================
  private boolean Implies()
    {
      begin("Implies");
      if (!next('>')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  Not = "!" ;
  //=====================================================================
  private boolean Not()
    {
      begin("Not");
      if (!next('!')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  Predicate = letter [0-9]* {predicate} ;
  //=====================================================================
  private boolean Predicate()
    {
      begin("Predicate");
      if (!letter()) return reject();
      while (nextIn('0','9'));
      sem.predicate();
      return accept();
    }
  
  //=====================================================================
  //  letter = lowerCase / upperCase ;
  //=====================================================================
  private boolean letter()
    {
      begin("letter");
      if (lowerCase()) return accept();
      if (upperCase()) return accept();
      return reject();
    }
  
  //=====================================================================
  //  lowerCase = [a-z] ;
  //=====================================================================
  private boolean lowerCase()
    {
      begin("lowerCase");
      if (!nextIn('a','z')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  upperCase = [A-Z] ;
  //=====================================================================
  private boolean upperCase()
    {
      begin("upperCase");
      if (!nextIn('A','Z')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  Space = " " ;
  //=====================================================================
  private boolean Space()
    {
      begin("Space");
      if (!next(' ')) return reject();
      return accept();
    }
  
  //=====================================================================
  //  False = "FALSE" ;
  //=====================================================================
  private boolean False()
    {
      begin("False");
      if (!next("FALSE")) return reject();
      return accept();
    }
  
}