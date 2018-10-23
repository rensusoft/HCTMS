package com.rensu.education.hctms.teach.service;


import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class DocumentCompare
{
  private String htmlResult;
  public String modify_writer;
  public String modify_time;
  public String sign = "";
  private List specialWordList;
  private List separatorCharList;

  public DocumentCompare()
  {
    this.specialWordList = new Vector();
    this.separatorCharList = new Vector();

    setSeparatorCharList();
    setSpecialWordList();
  }

  public void Compare(String oldd, String newd)
  {
    this.htmlResult = "";
    List oldd_list = null;
    List newd_list = null;

    oldd_list = getDocParagraph(oldd); //获得通过换行符将HTML文本分割成的集合（左）
    newd_list = getDocParagraph(newd); //获得通过换行符将HTML文本分割成的集合（右）

    List newd_Search_Range = getNewSearchRange(newd_list, oldd_list);

    List oldNoByUse = getDeleteNumberList(newd_Search_Range, oldd_list);

    for (int i = 0; i < newd_Search_Range.size(); i++) {
      String tempStr = (String)newd_Search_Range.get(i);
      if (tempStr.indexOf("~") > 0) {
        int temp1 = Integer.parseInt(tempStr.substring(0, tempStr.indexOf("~")));
        int temp2 = Integer.parseInt(tempStr.substring(tempStr.indexOf("~") + 1, tempStr.length()));

        addDeleteContent(oldNoByUse, oldd_list, temp1);
        if (temp1 > temp2) {
          this.htmlResult += getStyled(1, newd_list.get(i));
        }
        else {
          String tempOlddStr = "";
          String tempNewdStr = "";
          for (int m = temp1; m <= temp2; m++) {
            tempOlddStr = tempOlddStr + (String)oldd_list.get(m);
          }
          for (int m = i; m < newd_Search_Range.size(); m++) {
            tempNewdStr = tempNewdStr + (String)newd_list.get(m);
            if (m + 1 == newd_Search_Range.size()) {
              break;
            }
            if (!tempStr.equals((String)newd_Search_Range.get(m + 1)))
              break;
            i++;
          }

          CompareSentence(tempOlddStr, tempNewdStr);
        }
      }
      else {
        addDeleteContent(oldNoByUse, oldd_list, Integer.parseInt(tempStr));
        this.htmlResult += getStyled(0, newd_list.get(i));
      }
    }
  }

  public void CompareSentence(String oldd, String newd)
  {
    List oldd_list = null;
    List newd_list = null;

    oldd_list = getDocSentence(oldd);
    newd_list = getDocSentence(newd);

    List newd_Search_Range = getNewSearchRange(newd_list, oldd_list);

    List oldNoByUse = getDeleteNumberList(newd_Search_Range, oldd_list);

    for (int i = 0; i < newd_Search_Range.size(); i++) {
      String tempStr = (String)newd_Search_Range.get(i);
      if (tempStr.indexOf("~") > 0) {
        int temp1 = Integer.parseInt(tempStr.substring(0, tempStr.indexOf("~")));
        int temp2 = Integer.parseInt(tempStr.substring(tempStr.indexOf("~") + 1, tempStr.length()));

        addDeleteContent(oldNoByUse, oldd_list, temp1);
        if (temp1 > temp2) {
          this.htmlResult += getStyled(1, newd_list.get(i));
        }
        else {
          String tempOlddStr = "";
          String tempNewdStr = "";
          for (int m = temp1; m <= temp2; m++) {
            tempOlddStr = tempOlddStr + (String)oldd_list.get(m);
          }
          for (int m = i; m < newd_Search_Range.size(); m++) {
            tempNewdStr = tempNewdStr + (String)newd_list.get(m);
            if (m + 1 == newd_Search_Range.size()) {
              break;
            }
            if (!tempStr.equals((String)newd_Search_Range.get(m + 1)))
              break;
            i++;
          }

          CompareCharacter(tempOlddStr, tempNewdStr);
        }
      }
      else {
        addDeleteContent(oldNoByUse, oldd_list, Integer.parseInt(tempStr));
        this.htmlResult += getStyled(0, newd_list.get(i));
      }
    }
  }

  public void CompareCharacter(String oldd, String newd)
  {
    List oldd_list = null;
    List newd_list = null;

    oldd_list = getDocChar(oldd);
    newd_list = getDocChar(newd);

    List newd_Search_Range = getNewSearchRange(newd_list, oldd_list);

    List oldNoByUse = getDeleteNumberList(newd_Search_Range, oldd_list);

    for (int i = 0; i < newd_Search_Range.size(); i++) {
      String tempStr = (String)newd_Search_Range.get(i);
      if (tempStr.indexOf("~") > 0) {
        int temp1 = Integer.parseInt(tempStr.substring(0, tempStr.indexOf("~")));
        int temp2 = Integer.parseInt(tempStr.substring(tempStr.indexOf("~") + 1, tempStr.length()));

        addDeleteContent(oldNoByUse, oldd_list, temp1);
        if (temp1 > temp2) {
          this.htmlResult += getStyled(1, newd_list.get(i));
        } else {
          String modifyContent = "";
//          for (int mindex = temp1; mindex <= temp2; mindex++) {
//            modifyContent = modifyContent + (String)oldd_list.get(mindex);
//          }
          if (!modifyContent.equals("")) {
            modifyContent = "(" + modifyContent + ")";
          }
          String newContent = "";
          for (int newindex = i; newindex < newd_Search_Range.size(); newindex++) {
            if (tempStr.equals((String)newd_Search_Range.get(newindex))) {
              i = newindex;
              newContent = newContent + (String)newd_list.get(i);
            }
          }
          this.htmlResult += getStyled(3, new StringBuffer(String.valueOf(modifyContent)).append(newContent).toString());
        }
      }
      else {
        addDeleteContent(oldNoByUse, oldd_list, Integer.parseInt(tempStr));
        this.htmlResult += getStyled(0, newd_list.get(i));
      }
    }
    for (int i = 0; i < oldNoByUse.size(); i++)
    {
      this.htmlResult += getStyled(2, oldd_list.get(Integer.parseInt((String)oldNoByUse.get(i))));
    }
  }

  private void addDeleteContent(List oldNoByUse, List oldd_list, int Number)
  {
    for (int n = 0; n < oldNoByUse.size(); n++) {
      int tempOldNoUse = Integer.parseInt((String)oldNoByUse.get(n));
      if (Number < tempOldNoUse) break;
      this.htmlResult += getStyled(2, oldd_list.get(tempOldNoUse));
      oldNoByUse.remove(n);
      n--;
    }
  }

  private List getDeleteNumberList(List newd_Search_Range, List oldd_list)
  {
    List oldNoByUse = new Vector();
    for (int m = 0; m < oldd_list.size(); m++)
    {
      int i = 0;
      for (; i < newd_Search_Range.size(); i++) {
        String tempStr = (String)newd_Search_Range.get(i);
        if (tempStr.indexOf("~") > 0) {
          int temp1 = Integer.parseInt(tempStr.substring(0, tempStr.indexOf("~")));
          int temp2 = Integer.parseInt(tempStr.substring(tempStr.indexOf("~") + 1, tempStr.length()));
          if ((m == temp1) || (m == temp2)) break; if ((m > temp1) && (m < temp2))
            break;
        }
        else {
          int temp3 = Integer.parseInt(tempStr);
          if (m == temp3) {
            break;
          }
        }
      }
      if (i == newd_Search_Range.size()) {
        oldNoByUse.add(m+"");
      }
    }

    return oldNoByUse;
  }

  private String getStyled(int flag, Object value)
  {
    String result = "";
    String bgcolor = "#C0C0C0";
    String color = "";
    switch (flag)
    {
    case 0:
      bgcolor = "#FFFFFF";
      color = "#000000";
      result = value+"";
      //result = "<font style=\"background=" + bgcolor + ";\" color=" + color + ">" + value + "</font>";
      break;
    case 1:
      color = "#66CD00";
      result = "<font style=\"background=" + bgcolor + ";\" color=" + color + " title='" + this.sign + "新增'>" + value + "</font>";
      break;
    case 2:
      color = "#0000FF";
      value = "<strike>" + (String)value + "</strike>";
      result = "<span xtype='del'><font style=\"background=" + bgcolor + ";\" color=" + color + " title='" + this.sign + "删除'>" + value + "</font></span>";
      break;
    case 3:
      color = "#FF0000";
      result = "<font style=\"background=" + bgcolor + ";\" color=" + color + " title='" + this.sign + "修改'>" + value + "</font>";
    }

    return result;
  }

  private List getNewSearchRange(List newd_list, List oldd_list)
  {
    String oldd_paragraph = "";
    String newd_paragraph = "";
    List newd_Search_Range = new Vector();
    int i = 0; for (int oldd_index = 0; i < newd_list.size(); i++)
    {
      newd_paragraph = (String)newd_list.get(i);
      oldd_paragraph = "";
      int j = oldd_index;
      for (; (newd_paragraph != null) && (j < oldd_list.size()); j++) {
        oldd_paragraph = (String)oldd_list.get(j);

        if (newd_paragraph.equals(oldd_paragraph)) {
          break;
        }
      }
      if (j == oldd_list.size()) {
        newd_Search_Range.add(oldd_index + "~" + (j - 1));
      } else {
        newd_Search_Range.add(j+"");
        if (i > 0) {
          for (int lastindex = i; lastindex > 0; lastindex--) {
            String tempStr = (String)newd_Search_Range.get(lastindex - 1);
            if (tempStr.indexOf("~") <= 0) break;
            String tempStr2 = tempStr.substring(tempStr.indexOf("~") + 1, tempStr.length());
            if (j - 1 < Integer.parseInt(tempStr2)) {
              newd_Search_Range.set(lastindex - 1, tempStr.substring(0, tempStr.indexOf("~")) + "~" + (j - 1));
            }

          }

        }

        oldd_index = j + 1;
      }
    }

    return newd_Search_Range;
  }

  private List getDocParagraph(String str) //通过换行符将HTML文本分割成集合
  {
    List list = new Vector();
    list.addAll(Arrays.asList(str.split("\n")));

    return list;
  }

  private List getDocSentence(String str)
  {
    List list = new Vector();

    int star_index = 0;
    int last_index = 0;
    try {
      while (getSpecCharIndexOf(str, star_index) >= 0) {
        last_index = getSpecCharIndexOf(str, star_index);
        list.add(str.substring(star_index, last_index + 1));
        star_index = last_index + 1;
      }

      if (list.size() == 0)
        list.add(str);
      else if (str.length() > last_index)
        list.add(str.substring(star_index, str.length()));
    }
    catch (RuntimeException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  private List getDocChar(String str)
  {
    List list = new Vector();

    List specChar = this.specialWordList;

    boolean number = false;
    boolean en = false;
    boolean haveSpecChar = false;
    try {
      String tempStr = "";
      String specStr = "";
      for (int i = 0; i < str.length(); i++) {
        for (int j = 0; j < specChar.size(); j++) {
          haveSpecChar = false;
          specStr = (String)specChar.get(j);
          if (str.substring(i, str.length()).indexOf(specStr) == 0) {
            list.add(specStr);
            i = i + specStr.length() - 1;
            haveSpecChar = true;
            break;
          }
        }
        if (haveSpecChar)
        {
          continue;
        }
        tempStr = str.substring(i, i + 1);

        if ((tempStr.compareTo("0") >= 0) && (tempStr.compareTo("9") <= 0)) {
          if (number) {
            String tempStrNumber = (String)list.get(list.size() - 1) + tempStr;
            list.set(list.size() - 1, tempStrNumber);
          } else {
            list.add(tempStr);
          }
          number = true;
          en = false;
        } else if (((tempStr.compareTo("a") >= 0) && (tempStr.compareTo("z") <= 0)) || (
          (tempStr.compareTo("A") >= 0) && (tempStr.compareTo("Z") <= 0))) {
          if (en) {
            String tempStrNumber = (String)list.get(list.size() - 1) + tempStr;
            list.set(list.size() - 1, tempStrNumber);
          } else {
            list.add(tempStr);
          }
          number = false;
          en = true;
        } else {
          list.add(tempStr);
          number = false;
          en = false;
        }
      }
    }
    catch (RuntimeException e)
    {
      e.printStackTrace();
    }

    return list;
  }

  private int getSpecCharIndexOf(String str, int star_index)
  {
    List list = this.separatorCharList;
    List specChar = this.specialWordList;
    int return_index = -1;

    int index = 0;
    String separatorStr = "";
    String specCharStr = "";
    for (int i = 0; i < list.size(); i++) {
      separatorStr = (String)list.get(i);
      index = str.indexOf(separatorStr, star_index);
      if ((index == -1) || (
        (return_index != -1) && (return_index <= index))) continue;
      boolean flag = true;
      for (int j = 0; j < specChar.size(); j++) {
        specCharStr = (String)specChar.get(j);
        if (str.indexOf(specCharStr, index) == index) {
          flag = false;
          break;
        }
        int m = specCharStr.indexOf(separatorStr);
        if ((m >= 0) && (index - m >= 0) && (str.indexOf(specCharStr, index - m) == index - m)) {
          flag = false;
          break;
        }
      }

      if (flag) {
        return_index = index;
      }
    }
    return return_index;
  }

  public String getHtmlResult() {
    return this.htmlResult;
  }

  public static void main(String[] args)
  {
    String s1 = "<font style=\"font-family: 微软雅黑; font-size: 14px;\"><strong>主诉</strong>：wewewew<br><br><strong>现病史</strong>：ddsfddsds<br><br><strong>既往史</strong>：sdf sdf <br><br><strong>家族史</strong>：sdf sdf <br><br><strong>体格检查</strong>：sdf sdf <br><br></font>";
    String s2 = "<font style=\"font-family: 微软雅黑; font-size: 14px;\"><strong>主诉</strong>：we22w<br><br><strong>现病史</strong>：ddsfddsds<br><br><strong>既往史</strong>：sdf sdf <br><br><strong>家族史</strong>：sdf f <br><br><strong>体格检查</strong>：sdf11 sdf <br><br></font>";

    //s1 = "=<br/>&nbsp;6&nbsp;&nbsp;&nbsp;4>5&nbsp;<br>Ⅰ助：&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;Ⅱ助：&nbsp;达到&nbsp;其他助手：&nbsp;  是&nbsp;麻醉医师：&nbsp;d&nbsp;开始时间：&nbsp;d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：&nbsp; d&nbsp;全程时间：&nbsp;s&nbsp;时&nbsp;d&nbsp;分&nbsp;术后诊断：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c &nbsp;导管及引流：&nbsp;d&nbsp;病理检查物：&nbsp;d&nbsp;手术经过：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d";
    //s2 = "=&nbsp;&nbsp;&nbsp;&nbsp;5>4&nbsp;<br/><b>Ⅰ助：<b/>&nbsp;黄洪峰&nbsp;&nbsp;&nbsp;&nbsp;Ⅱ助：&nbsp;陈讲话&nbsp;其他助手：&nbsp;  是&nbsp;麻醉医师：&nbsp;d&nbsp;开始时间：&nbsp;d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：&nbsp; d&nbsp;全程时间：&nbsp;s&nbsp;时&nbsp;d&nbsp;分&nbsp;术后诊断：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c &nbsp;导管及引流：&nbsp;d&nbsp;病理检查物：&nbsp;d&nbsp;手术经过：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d";
    //s1 = "患者：×××，男，56岁，以“外伤致右踝疼痛、肿胀1小时”为主诉入院。 （一）病例特点：  1、老年男性，原有心脏病史，生活自理，血压正常。  2、1小时前车祸致右踝扭伤，伤后不敢站里行走，右踝肿胀、疼痛剧烈。  3、查体：体温36.50C脉搏87次／分呼吸23次／分血压130／80mmHg,精神好，右踝肿胀，畸形不明显，环形压痛，可闻及骨擦音，踝关节活动受限，足背动脉搏动好，足趾活动正常。  4、X线示（2010.03.25.本院）：右内、外踝骨折并踝关节半脱位。  （二）拟诊讨论：  1、初步诊断：右内、外踝骨折并踝关节半脱位 2、诊断依据：  ⑴车祸致右踝扭伤、疼痛、肿胀1小时。  ⑵环形压痛，骨摩擦音，肿胀，活动受限。 ⑶X线示（2010.03.25.本院）：右内、外踝骨折并踝关节半脱位。  3、鉴别诊断：  ⑴踝关节韧带损伤：但踝关节主、被动活动存在，X线可有内、外翻应力试验阳性，表示韧带损伤，但骨质无中断。";
    //s2 = "患者：×××，女，57岁，以“外伤致右踝疼痛、肿胀1小时”为主诉入院。 （一）病例特点：  1、老年男性，原有心脏病史，生活自理。  2、1小时前车祸致右踝扭伤，伤后不敢站里行走，右踝肿胀、疼痛剧烈。  3、查体：体温36.50C，脉搏87次／分呼吸23次／分血压130／80mmHg,精神好，右踝肿胀，畸形不明显，环形压痛，可闻及骨擦音，踝关节活动受限，足背动脉搏动好，足趾活动正常。  4、X线示（2010.03.25.本院）：右内、外踝骨折并踝关节半脱位。并有骨折可能。  （二）拟诊讨论：  1、初步诊断：右内、外踝骨折并踝关节半脱位 2、诊断依据：  ⑴车祸致右踝扭伤、疼痛、肿胀1小时。  ⑵环形压痛，骨摩擦音，肿胀，活动受限。 ⑶X线示（2010.03.25.本院）：右内、外踝骨折并踝关节半脱位。  3、鉴别诊断：  ⑴踝关节韧带损伤：但踝关节主、被动活动存在，X线可有内、外翻应力试验阳性，表示韧带损伤，但骨质无中断。";
    DocumentCompare docCompare = new DocumentCompare();
    
    docCompare.Compare(s1, s2);
    String a = docCompare.getHtmlResult();
    System.out.println(a);
  }

  public void setSeparatorCharList()
  {
    this.separatorCharList.add("　");
    this.separatorCharList.add(" ");
    this.separatorCharList.add(",");
    this.separatorCharList.add("，");
    this.separatorCharList.add(".");
    this.separatorCharList.add("。");
    this.separatorCharList.add(";");
    this.separatorCharList.add("；");
    this.separatorCharList.add(":");
    this.separatorCharList.add("：");
    this.separatorCharList.add("?");
    this.separatorCharList.add("？");
    this.separatorCharList.add("+");
    this.separatorCharList.add("＋");
    this.separatorCharList.add("-");
    this.separatorCharList.add("－");
    this.separatorCharList.add("*");
    this.separatorCharList.add("×");
    this.separatorCharList.add("/");
    this.separatorCharList.add("÷");
    this.separatorCharList.add("、");
    this.separatorCharList.add("<");
    this.separatorCharList.add(">");
    this.separatorCharList.add("＜");
    this.separatorCharList.add("＞");
    this.separatorCharList.add("(");
    this.separatorCharList.add("（");
    this.separatorCharList.add(")");
    this.separatorCharList.add("）");
    this.separatorCharList.add("&");
    this.separatorCharList.add("＆");
    this.separatorCharList.add("!");
    this.separatorCharList.add("！");
    this.separatorCharList.add("$");
    this.separatorCharList.add("￥");
    this.separatorCharList.add("\n");
  }

  public void setSpecialWordList()
  {
    this.specialWordList.add("&nbsp;");
    this.specialWordList.add("&amp;");
    this.specialWordList.add("<br>");
    this.specialWordList.add("<br/>");
    this.specialWordList.add("<b>");
    this.specialWordList.add("</b>");
  }
}
