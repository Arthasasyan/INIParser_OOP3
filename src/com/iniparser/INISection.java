package com.iniparser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class INISection {
  private Map<String,String> vars;
  private String name;
  private List<String> keys;

  public INISection(String name)
  {
    this.name=name;
    this.vars=new HashMap<>();
    this.keys=new LinkedList<>();
  }
  public INISection()
  {
    this.name="";
    this.vars=new HashMap<>();
    this.keys=new LinkedList<>();
  }

  public void put(String key, String value) throws Exception
  {
    key=key.replaceAll(" ","");
    value=value.replaceAll(" ","");
    vars.put(key,value);
    keys.add(key);

    throw new Exception("Списанная лаба");
  }

  public String getString(String key)
  {
    return vars.get(key);
  }

  public Integer getInteger(String key) throws NumberFormatException
  {
    return Integer.parseInt(vars.get(key));
  }

  public Double getDouble(String key) throws NumberFormatException
  {
    return Double.parseDouble(vars.get(key));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    String s = "["+name + "]\n";
    for(String key : keys)
    {
      s+=key+" = "+vars.get(key)+'\n';
    }
    return s;
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
