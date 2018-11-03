package com.iniparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class INIFile {
  private String filePath;
  private Map<String, INISection> sections;
  private List<String> keys;

  public INIFile(String filePath) throws FileNotFoundException, IOException
  {
    this.filePath=filePath;
    File file = new File(filePath);
    this.keys=new LinkedList<>();
    if(!file.exists())
    {
      throw new FileNotFoundException("File not found");
    }
    sections=new HashMap<>();
    FileReader fileReader = new FileReader(file);
    Scanner sc = new Scanner(fileReader);
    INISection currentSection = null;
    while(sc.hasNextLine())
    {
      String s = sc.nextLine();
      if(s.startsWith("[")) //if is section name
      {
        String name = s.split(";")[0].split("\\[")[1];
        if(!(name.split("\\]").length<2))
        {
          throw new IOException("Wrong file type");
        }
        name=name.split("\\]")[0];
        currentSection = new INISection(name); //geeting section name
        sections.put(currentSection.getName(), currentSection);
        keys.add(name);
        continue;
      }
      else if(s.startsWith(";") || s.startsWith(" "))
      {
        continue;
      }
      else
      {
        String name = null;
        String value = null;
        String[] split = s.split(";")[0].split("=");
        if(split.length!=2)
        {
          throw new IOException("Wrong file type");
        }
        name=split[0];
        value=split[1];
        if(name==null || value==null || currentSection==null)
        {
          throw new IOException("Wrong file type");
        }

        currentSection.put(name,value);
      }
    }
  }

  public INISection get(String name)
  {
    return sections.get(name);
  }

  public String getFilePath() {
    return filePath;
  }

  @Override
  public String toString() {
    String answer = "";
    for(String key : keys)
    {
      answer+=sections.get(key).toString();
    }
    return answer;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj==null)
    {
      return false;
    }
    return obj.toString().equals(this.toString());
  }

  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }
}
