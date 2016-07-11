package com.github.lwhite1.tablesaw.reducing.functions;

import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.reducing.NumericReduceFunction;
import com.github.lwhite1.tablesaw.table.ViewGroup;

/**
 *
 */
public abstract class SummaryFunction {

  private final Table original;
  private final String summarizedColumnName;

  public SummaryFunction(Table original, String summarizedColumnName) {
    this.original = original;
    this.summarizedColumnName = summarizedColumnName;
  }

  String summarizedColumnName() {
    return summarizedColumnName;
  }

  Table original() {
    return original;
  }

  public String resultColumnName() {
    return summaryFunctionName() + " " + summarizedColumnName();
  }

  public Table by(String... columnNames) {
    ViewGroup group = ViewGroup.create(original(), columnNames);
    return group.reduce(summarizedColumnName(), function());
  }

  public abstract String summaryFunctionName();

  public abstract NumericReduceFunction function();
}
