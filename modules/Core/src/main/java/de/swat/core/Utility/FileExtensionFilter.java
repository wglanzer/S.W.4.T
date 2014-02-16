package de.swat.core.Utility;

import java.io.*;

public class FileExtensionFilter implements FilenameFilter
{
  public String[] acceptedExtensions;

  public FileExtensionFilter(String[] acceptedExtensions)
  {
    this.acceptedExtensions = acceptedExtensions;
  }

  public boolean accept(File dir, String name)
  {
    if (acceptedExtensions == null) return false;
    for (int i = 0; i < acceptedExtensions.length; i++)
    {
      if (name.endsWith(acceptedExtensions[i])) return true;
    }
    return false;
  }
};