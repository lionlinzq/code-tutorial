package pers.lionlinzq.excel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BasicTest {


    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(new File("./info.xlsx"));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        Workbook newBook = new XSSFWorkbook();
        Sheet newBookSheet = newBook.createSheet("统计数据");

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row oldRow = sheet.getRow(i);
            Row newBookSheetRow = newBookSheet.createRow(i);
            for (int j = 0; j < oldRow.getPhysicalNumberOfCells(); j++) {
                newBookSheetRow.createCell(j).setCellValue(oldRow.getCell(j).getStringCellValue());

                // 调整列宽
                newBookSheet.autoSizeColumn(j);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("new.xlsx");
        newBook.write(outputStream);
        newBook.close();
        outputStream.close();

        workbook.close();
        file.close();
    }

    @Test
    public void test(){
        String json = "[\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-05T02:42:17.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"客户--拜访工作记录\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_workrecord\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904876576494194766\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_workrecord\",\n" +
                "    \"updatetime\": \"2023-11-14T02:34:54.000+00:00\",\n" +
                "    \"updateaccountname\": \"钟晓仕\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 1472868812033495000,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-05T01:28:19.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--计划拜访表\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_planvisit\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904876576494194781\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_planvisit\",\n" +
                "    \"updatetime\": \"2023-11-10T08:19:15.000+00:00\",\n" +
                "    \"updateaccountname\": \"俞允\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 1085802995557470200,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-19T09:12:43.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"客户--拜访状态\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_customerstatus\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"910049676986814563\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_customerstatus\",\n" +
                "    \"updatetime\": \"2022-07-27T14:32:49.000+00:00\",\n" +
                "    \"updateaccountname\": \"钟晓仕\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 1472868812033495000,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-05T02:08:03.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"客户--实际拜访表\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_actual\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904876576494194774\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_actual\",\n" +
                "    \"updatetime\": \"2022-07-27T14:32:48.000+00:00\",\n" +
                "    \"updateaccountname\": \"钟晓仕\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 1472868812033495000,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2022-03-16T08:02:47.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"客户拜访状态(保留最近2个月数据)\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"bi_dwd_dsr_kx_visit_customerstatus\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1504003743992123490\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"bi_dwd_dsr_kx_visit_customerstatus\",\n" +
                "    \"updatetime\": \"2022-03-16T08:04:02.000+00:00\",\n" +
                "    \"updateaccountname\": \"黄崇文\",\n" +
                "    \"objectdescr\": \"bi 报表数据加工kx_visit_customerstatus，最近2个月数据\",\n" +
                "    \"createaccountname\": \"黄崇文\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2022-03-08T08:12:09.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访记录中间表\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_workrecord_exec\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1500738245753966658\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_workrecord_exec\",\n" +
                "    \"updatetime\": \"2022-03-08T08:59:48.000+00:00\",\n" +
                "    \"updateaccountname\": \"黄崇文\",\n" +
                "    \"objectdescr\": \"积分统计，拜访次数\",\n" +
                "    \"createaccountname\": \"黄崇文\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2019-06-05T07:57:25.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"通用拜访--通用采集表\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_commoncollect\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1098054025380761697\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_commoncollect\",\n" +
                "    \"updatetime\": \"2020-09-18T03:38:06.000+00:00\",\n" +
                "    \"updateaccountname\": \"谭锦标\",\n" +
                "    \"objectdescr\": \"通用采集表。存储所有拜访采集信息的数据的【业务实体】\",\n" +
                "    \"createaccountname\": \"梁海棠\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2018-05-28T10:46:22.000+00:00\",\n" +
                "    \"createaccountcode\": 49,\n" +
                "    \"objectname\": \"被动定位--数据采集\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_daemonlocation\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1001038606539821155\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_daemonlocation\",\n" +
                "    \"updatetime\": \"2019-09-16T08:32:55.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"胡宗勇\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-04T09:53:59.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--拜访策略\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_strategy\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904632581130162231\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_strategy\",\n" +
                "    \"updatetime\": \"2019-07-17T03:22:28.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-05T01:23:20.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--临时计划客户\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_temporary\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904876576494194787\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_temporary\",\n" +
                "    \"updatetime\": \"2019-07-17T03:22:19.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2019-06-06T06:37:36.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--拜访计划关联路线表\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"tn_kx_visit_plan_line\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1103103583861215312\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"tn_kx_visit_plan_line\",\n" +
                "    \"updatetime\": \"2019-07-17T03:21:44.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"胡宗勇\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2019-06-06T06:37:36.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--调整计划表\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"tn_kx_visit_matual\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1103117003973070942\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"tn_kx_visit_matual\",\n" +
                "    \"updatetime\": \"2019-07-17T03:21:32.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"胡宗勇\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2019-06-06T06:37:36.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--拜访计划表\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"tn_kx_visit_plan\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1103103583861215321\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"tn_kx_visit_plan\",\n" +
                "    \"updatetime\": \"2019-07-17T03:21:15.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"胡宗勇\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-04T09:43:05.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--人员线路\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_line\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904632581130162242\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_line\",\n" +
                "    \"updatetime\": \"2019-07-17T03:20:47.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2017-09-04T09:46:01.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"拜访计划--线路客户\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_linecustomer\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"904632581130162236\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_linecustomer\",\n" +
                "    \"updatetime\": \"2019-07-17T03:20:35.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2018-05-31T08:16:54.000+00:00\",\n" +
                "    \"createaccountcode\": 15,\n" +
                "    \"objectname\": \"被动定位--监控人员调度\",\n" +
                "    \"directorytypecode\": \"1122420444306121002\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_monitormemberlist\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1001644163994882125\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访计划\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_monitormemberlist\",\n" +
                "    \"updatetime\": \"2019-07-17T03:16:29.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"梁志伟\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2018-05-29T09:27:30.000+00:00\",\n" +
                "    \"createaccountcode\": 15,\n" +
                "    \"objectname\": \"被动定位--监控人员\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_monitormember\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1001352057262837858\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"0\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_monitormember\",\n" +
                "    \"updatetime\": \"2019-07-17T03:14:45.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"\",\n" +
                "    \"createaccountname\": \"梁志伟\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createtime\": \"2019-06-05T07:57:25.000+00:00\",\n" +
                "    \"createaccountcode\": 0,\n" +
                "    \"objectname\": \"通用拜访--失访\",\n" +
                "    \"directorytypecode\": \"1122420444306121003\",\n" +
                "    \"parentobjectcode\": null,\n" +
                "    \"objectmark\": \"kx_visit_lost\",\n" +
                "    \"datapattern\": 1,\n" +
                "    \"objectcode\": \"1103924630269333603\",\n" +
                "    \"metamodelcode\": \"1000000000000000090\",\n" +
                "    \"directorytypename\": \"拜访采集\",\n" +
                "    \"hasoffline\": \"1\",\n" +
                "    \"marktype\": 1,\n" +
                "    \"objectcatgory\": 2,\n" +
                "    \"objecttypecode\": \"1\",\n" +
                "    \"publishstatus\": 1,\n" +
                "    \"objecttemplatecode\": null,\n" +
                "    \"tablename\": \"kx_visit_lost\",\n" +
                "    \"updatetime\": \"2019-07-17T02:43:38.000+00:00\",\n" +
                "    \"updateaccountname\": \"胡宗勇\",\n" +
                "    \"objectdescr\": \"失访\",\n" +
                "    \"createaccountname\": \"梁海棠\",\n" +
                "    \"status\": 1,\n" +
                "    \"updateaccountcode\": 0,\n" +
                "    \"confsource\": 1\n" +
                "  }\n" +
                "]";
        JSONArray objects = JSON.parseArray(json);
        Object[] array = objects.toArray();
        System.out.println("名称\t实体编码\t数据库表名");

        for (int i = 0; i < array.length; i++) {
            String string = array[i].toString();
            JSONObject jsonObject = (JSONObject) JSONObject.parse(string);
            System.out.println(jsonObject.get("objectname")+"\t"+jsonObject.get("objectcode")+"\t"+jsonObject.get("tablename"));
        }
    }



}
