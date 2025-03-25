package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
    }

    public static Map<String, Object> jsonToMap(String src) {
        try {
            return mapper.readValue(src, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> toMap(String src) throws Exception {
        return mapper.readValue(src, new TypeReference<Map<String, String>>() {
        });
    }

    public static Map<String, Object> toMapValObj(String src) throws Exception {
        return mapper.readValue(src, new TypeReference<Map<String, Object>>() {
        });
    }

    public static void main(String[] args) throws Exception {

        String json1 = "{\n" +
                "    \"rolecode\": \"123456789\",\n" +
                "    \"rolename\": \"渠道员-change\",\n" +
                "    \"roletype\":\"3\",\n" +
                "    \"directorytype\": \"5\",\n" +
                "    \"functions\":[ {\n" +
                "        \"functioncode\":\"f1\",\n" +
                "        \"functionname\":\"功能点F1\",\n" +
                "        \"status\": 2\n" +
                "    },{\n" +
                "        \"functioncode\":\"f2\",\n" +
                "        \"functionname\":\"功能点F2\",\n" +
                "        \"status\": 1\n" +
                "    }, {\n" +
                "        \"functioncode\":\"f3\",\n" +
                "        \"functionname\":\"功能点F3\",\n" +
                "        \"status\": 1\n" +
                "    }],\n" +
                "    \"menus\": [ {\n" +
                "        \"menucode\": \"a\", \n" +
                "        \"status\": 2 \n" +
                "    },{\n" +
                "        \"menucode\": \"b\", \n" +
                "        \"menuname\": \"bname\", \n" +
                "        \"status\": 1 \n" +
                "    },{\n" +
                "        \"menucode\": \"c\", \n" +
                "        \"menuname\": \"c菜单add\", \n" +
                "        \"status\": 1 \n" +
                "    }], \n" +
                "    \"datarights\": [{\n" +
                "        \"datarightcode\":\"d1\",\n" +
                "        \"datarightname\":\"数据规则一-change\",\n" +
                "        \"rule\": [ {\n" +
                "            \"code\": \"r1\",\n" +
                "            \"data\": {\"a\": \"avalue\", \"b\": \"bvalue\"},\n" +
                "            \"status\": 1\n" +
                "        }, {\n" +
                "            \"code\": \"r2\",\n" +
                "            \"data\": {\"a\": \"r2-avalue\", \"b\": \"r2-bvalue-change\" , \"c\": { \"c-code\": \"abc\", \"c-name\": \"cname\", \"c-value\": \"cvalue-change\"}},\n" +
                "            \"status\": 1\n" +
                "        }],\n" +
                "        \"status\": 1,\n" +
                "        \"ranktype\": 3\n" +
                "        }],\n" +
                "        \"fieldrights\": [{\n" +
                "            \"fieldrightcode\": \"343432\",\n" +
                "            \"fieldname\": \"总金额-change\",\n" +
                "            \"fieldtype\": 2,\n" +
                "            \"status\": 1 \n" +
                "        }, {\n" +
                "            \"fieldrightcode\": \"34343244\",\n" +
                "            \"fieldname\": \"率-add\",\n" +
                "            \"fieldtype\": 1,\n" +
                "            \"status\": 1\n" +
                "        }],\n" +
                "    \"status\": 1\n" +
                "}";
        String json2 = "{\n" +
                "    \"rolecode\": \"123456789\",\n" +
                "    \"rolename\": \"渠道员\",\n" +
                "    \"roletype\":\"3\",\n" +
                "    \"directorytype\": \"5\",\n" +
                "    \"functions\":[ {\n" +
                "        \"functioncode\":\"f1\",\n" +
                "        \"functionname\":\"功能点F1\",\n" +
                "        \"status\": 1\n" +
                "    },{\n" +
                "        \"functioncode\":\"f2\",\n" +
                "        \"functionname\":\"功能点F2\",\n" +
                "        \"status\": 1\n" +
                "    }],\n" +
                "    \"menus\": [ {\n" +
                "        \"menucode\": \"a\", \n" +
                "        \"menuname\": \"a\", \n" +
                "        \"status\": 1 \n" +
                "    },{\n" +
                "        \"menucode\": \"b\", \n" +
                "        \"menuname\": \"b\", \n" +
                "        \"status\": 1 \n" +
                "    }], \n" +
                "    \"datarights\": [{\n" +
                "        \"datarightcode\":\"d1\",\n" +
                "        \"datarightname\":\"数据规则一\",\n" +
                "        \"rule\": [ {\n" +
                "            \"code\": \"r1\",\n" +
                "            \"data\": {\"a\": \"avalue\", \"b\": \"bvalue\"},\n" +
                "            \"status\": 1\n" +
                "        }, {\n" +
                "            \"code\": \"r2\",\n" +
                "            \"data\": {\"a\": \"r2-avalue\", \"b\": \"r2-bvalue\", \"c\": { \"c-code\": \"abc\", \"c-name\": \"cname\", \"c-value\": \"cvalue\"}},\n" +
                "            \"status\": 1\n" +
                "        }],\n" +
                "        \"status\": 1,\n" +
                "        \"ranktype\": 1\n" +
                "        }],\n" +
                "        \"fieldrights\": [{\n" +
                "            \"fieldrightcode\": \"343432\",\n" +
                "            \"fieldname\": \"金额\",\n" +
                "            \"fieldtype\": 1,\n" +
                "            \"status\": 1 \n" +
                "        }],\n" +
                "    \"status\": 1\n" +
                "}";

        String json3 = "{\n" +
                "  \"resp_data\": {\n" +
                "    \"objectcode\": \"829602839767532636\",\n" +
                "    \"offlinetype\": \"2\",\n" +
                "    \"offlinemodelcode\": \"629602839767531001\",\n" +
                "    \"offlinemodelname\": \"营销区域-离线模型\",\n" +
                "    \"properties\": [\n" +
                "      \"829609839767534111\",\n" +
                "      \"829609839767534121\",\n" +
                "      \"829609839767534112\",\n" +
                "      \"829609839767534115\",\n" +
                "      \"829609839767534120\",\n" +
                "      \"829609839767534113\",\n" +
                "      \"829609839767534114\",\n" +
                "      \"829609839767534122\"\n" +
                "    ],\n" +
                "    \"status\": 1\n" +
                "  }\n" +
                "}";
        String json4 = "{\n" +
                "  \"resp_data\": {\n" +
                "    \"objectcode\": \"829602839767532636\",\n" +
                "    \"offlinetype\": \"2\",\n" +
                "    \"offlinemodelcode\": \"629602839767531001\",\n" +
                "    \"offlinemodelname\": \"营销区域-离线模型\",\n" +
                "    \"properties\": [\n" +
                "      \"829609839767534111\",\n" +
                "      \"829609839767534121\",\n" +
                "      \"829609839767534112\",\n" +
                "      \"829609839767534115\",\n" +
                "      \"829609839767534120\",\n" +
                "      \"829609839767534113\",\n" +
                "      \"829609839767534114\",\n" +
                "      \"829609839767534125\"\n" +
                "    ],\n" +
                "    \"status\": 1\n" +
                "  }\n" +
                "}";

        String page = "{\"view\":{\"body\":{\"flex\":\"\",\"type\":\"layout\",\"content\":[{\"searchcondition\":{\"advanced\":[],\"basic\":[{\"_ide_propertytypecode\":\"2\",\"code\":\"896938753782845539\",\"historyenable\":\"0\",\"hidden\":\"\",\"maxlength\":\"100\",\"_ide_name\":\"productcode\",\"titleflex\":\"\",\"type\":\"filtertextinput\",\"title\":\"产品编码\",\"_ide_componenttype\":\"input\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"\",\"placeholder\":\"产品编码\",\"value\":\"\",\"_type_ctrlarea\":\"filter_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"2\",\"code\":\"896938753782845538\",\"historyenable\":\"0\",\"hidden\":\"\",\"maxlength\":\"100\",\"_ide_name\":\"productname\",\"titleflex\":\"\",\"type\":\"filtertextinput\",\"title\":\"产品名称\",\"_ide_componenttype\":\"input\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"\",\"placeholder\":\"产品名称\",\"_type_ctrlarea\":\"filter_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"\",\"code\":\"896981458235822157\",\"hidden\":\"\",\"_ide_name\":\"\",\"titleflex\":\"\",\"type\":\"dropdownbox\",\"title\":\"品牌\",\"_ide_componenttype\":\"select\",\"required\":\"\",\"eventlist\":[{\"handler\":\"896981458235822151\",\"trigger\":\"onload\"},{\"handler\":\"\",\"trigger\":\"onvaluechange\"}],\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"1\",\"width\":\"\",\"options\":[],\"placeholder\":\"品牌\",\"text\":\"\",\"_type_ctrlarea\":\"\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"\",\"code\":\"896981458235822156\",\"hidden\":\"\",\"_ide_name\":\"\",\"titleflex\":\"\",\"type\":\"dropdownbox\",\"title\":\"品类\",\"_ide_componenttype\":\"select\",\"required\":\"\",\"eventlist\":[{\"handler\":\"896981458235822149\",\"trigger\":\"onload\"},{\"handler\":\"\",\"trigger\":\"onvaluechange\"}],\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"1\",\"width\":\"\",\"options\":[],\"placeholder\":\"品类\",\"text\":\"\",\"_type_ctrlarea\":\"\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"\",\"code\":\"896981458235822155\",\"hidden\":\"\",\"_ide_name\":\"\",\"titleflex\":\"\",\"type\":\"dropdownbox\",\"title\":\"重点产品\",\"_ide_componenttype\":\"select\",\"required\":\"\",\"eventlist\":[{\"handler\":\"\",\"trigger\":\"onload\"},{\"handler\":\"\",\"trigger\":\"onvaluechange\"}],\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"1\",\"width\":\"\",\"options\":[{\"text\":\"是\",\"key\":\"1\"},{\"text\":\"否\",\"key\":\"0\"}],\"placeholder\":\"重点产品\",\"text\":\"\",\"_type_ctrlarea\":\"\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"7\",\"code\":\"896938753782845536\",\"hidden\":\"\",\"max\":\"\",\"_ide_name\":\"validityperiod\",\"format\":\"\",\"titleflex\":\"\",\"type\":\"datepicker\",\"title\":\"新品有效期\",\"_ide_componenttype\":\"date\",\"required\":\"\",\"unit\":\"\",\"min\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"width\":\"\",\"marktype\":\"\",\"placeholder\":\"新品有效期\",\"value\":\"\",\"_type_ctrlarea\":\"filter_area\",\"status\":\"1\",\"titleRatio\":\"\"}]},\"code\":\"ctrl_table_filter\",\"marginhorizontal\":\"15\",\"marginvertical\":\"5\",\"hidden\":\"\",\"bindingctrl\":{\"code\":\"ctrl_table\",\"trigger\":\"onload\"},\"sortcondition\":{\"advanced\":[],\"basic\":[]},\"marktype\":\"\",\"type\":\"filter\",\"title\":\"查询栏\",\"_type_ctrlarea\":\"filter_area\",\"status\":\"1\"},{\"justifycontent\":\"spacebetween\",\"alignitems\":\"center\",\"flexdirection\":\"horizontal\",\"type\":\"layout\",\"content\":[{\"alignitems\":\"center\",\"flexdirection\":\"horizontal\",\"type\":\"layout\",\"content\":[{\"code\":\"ctrl_table_button_new\",\"marginhorizontal\":\"15\",\"hidden\":\"\",\"icon\":\"new\",\"bordervisible\":\"1\",\"type\":\"button\",\"title\":\"新增\",\"_ide_componenttype\":\"button\",\"eventlist\":[{\"handler\":\"handler_new\",\"trigger\":\"onclicked\"}],\"marginvertical\":\"5\",\"flex\":\"\",\"marktype\":\"\",\"width\":\"\",\"text\":\"新增\",\"_type_ctrlarea\":\"func_area\",\"status\":\"1\"},{\"code\":\"ctrl_table_button_del\",\"marginhorizontal\":\"5\",\"hidden\":\"\",\"icon\":\"delete\",\"bordervisible\":\"1\",\"type\":\"button\",\"title\":\"删除\",\"_ide_componenttype\":\"button\",\"marginright\":\"5\",\"eventlist\":[{\"handler\":\"handler_del\",\"trigger\":\"onclicked\"}],\"flex\":\"\",\"marktype\":\"\",\"width\":\"\",\"disabled\":\"1\",\"text\":\"删除\",\"marginleft\":\"10\",\"_type_ctrlarea\":\"func_area\",\"status\":\"1\"},{\"code\":\"ctrl_table_button_enable\",\"marginhorizontal\":\"5\",\"hidden\":\"\",\"icon\":\"enable\",\"bordervisible\":\"1\",\"type\":\"button\",\"title\":\"启用\",\"_ide_componenttype\":\"button\",\"eventlist\":[{\"handler\":\"handler_enable\",\"trigger\":\"onclicked\"}],\"marginvertical\":\"5\",\"flex\":\"\",\"marktype\":\"\",\"width\":\"\",\"disabled\":\"1\",\"text\":\"启用\",\"_type_ctrlarea\":\"func_area\",\"status\":\"1\"},{\"code\":\"ctrl_table_button_disable\",\"marginhorizontal\":\"5\",\"hidden\":\"\",\"icon\":\"disable\",\"bordervisible\":\"1\",\"type\":\"button\",\"title\":\"停用\",\"_ide_componenttype\":\"button\",\"eventlist\":[{\"handler\":\"handler_disable\",\"trigger\":\"onclicked\"}],\"marginvertical\":\"5\",\"flex\":\"\",\"marktype\":\"\",\"width\":\"\",\"disabled\":\"1\",\"text\":\"停用\",\"_type_ctrlarea\":\"func_area\",\"status\":\"1\"}]},{\"justifycontent\":\"flexend\",\"alignitems\":\"center\",\"flexdirection\":\"horizontal\",\"type\":\"layout\",\"content\":[]}]},{\"refreshable\":\"1\",\"eventlist\":[{\"handler\":\"handler_loadlistdata\",\"trigger\":\"onload\"},{\"handler\":\"handler_loadlistdata\",\"trigger\":\"onrefresh\"},{\"handler\":\"handler_loadlistdata\",\"trigger\":\"onloadmore\"},{\"handler\":\"handler_checkedchanged\",\"trigger\":\"onchecked\"}],\"code\":\"ctrl_table\",\"pagingable\":\"1\",\"checkable\":\"1\",\"flex\":\"1\",\"marktype\":\"\",\"type\":\"table\",\"rows\":{\"columnitems\":[{\"_ide_propertytypecode\":\"2\",\"code\":\"896938753782845535\",\"marginhorizontal\":\"5\",\"hidden\":\"\",\"_ide_name\":\"productcode\",\"icon\":\"enable\",\"bordervisible\":\"0\",\"titleflex\":\"\",\"type\":\"button\",\"title\":\"产品编码\",\"_ide_componenttype\":\"weblist-button\",\"required\":\"\",\"eventlist\":[{\"handler\":\"handler_linkdetail\",\"trigger\":\"onclicked\"}],\"marginvertical\":\"5\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"150\",\"marktype\":\"\",\"width\":\"150\",\"text\":\"产品编码\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"2\",\"code\":\"896938753782845534\",\"hidden\":\"\",\"_ide_name\":\"productname\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"产品名称\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"120\",\"marktype\":\"\",\"width\":\"120\",\"value\":\"\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"locationenable\":\"1\",\"_ide_propertytypecode\":\"16\",\"code\":\"896938753782845519\",\"watermark\":\"le:datetime()\",\"hidden\":\"\",\"_ide_name\":\"productimage\",\"titleflex\":\"\",\"source\":\"0\",\"type\":\"photo\",\"title\":\"产品图片\",\"_ide_componenttype\":\"weblist-photo\",\"required\":\"\",\"maxnumber\":\"5\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"200\",\"width\":\"200\",\"marktype\":\"\",\"compression\":\"\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"\",\"code\":\"896993702487134307\",\"hidden\":\"\",\"_ide_name\":\"\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"品牌\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"1\",\"width\":\"\",\"value\":\"\",\"_type_ctrlarea\":\"\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"\",\"code\":\"896993702487134306\",\"hidden\":\"\",\"_ide_name\":\"\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"品类\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"1\",\"width\":\"\",\"value\":\"\",\"_type_ctrlarea\":\"\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"3\",\"code\":\"896938753782845530\",\"hidden\":\"\",\"_ide_name\":\"distributionnorm\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"分销单位\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"200\",\"value\":\"\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"3\",\"code\":\"896938753782845529\",\"hidden\":\"\",\"_ide_name\":\"singlenorm\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"单品单位\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"200\",\"value\":\"\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"3\",\"code\":\"896938753782845531\",\"hidden\":\"\",\"_ide_name\":\"unitconverrate\",\"titleflex\":\"\",\"type\":\"text\",\"title\":\"单位转换率\",\"_ide_componenttype\":\"weblist-text\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"200\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"18\",\"code\":\"896938753782845522\",\"hidden\":\"\",\"_ide_name\":\"iskeyproduct\",\"titleflex\":\"\",\"type\":\"dynamictext\",\"title\":\"重点产品\",\"_ide_componenttype\":\"weblist-dynamictext\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"200\",\"options\":[{\"color\":\"\",\"icon\":\"\",\"text\":\"是\",\"key\":\"1\"},{\"color\":\"\",\"icon\":\"\",\"text\":\"否\",\"key\":\"0\"}],\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},{\"_ide_propertytypecode\":\"7\",\"code\":\"896938753782845521\",\"hidden\":\"\",\"max\":\"\",\"_ide_name\":\"validityperiod\",\"format\":\"\",\"titleflex\":\"\",\"type\":\"datepicker\",\"title\":\"新品有效期\",\"_ide_componenttype\":\"weblist-date\",\"required\":\"\",\"unit\":\"\",\"min\":\"\",\"readonly\":\"1\",\"flex\":\"\",\"titlewidth\":\"200\",\"width\":\"200\",\"marktype\":\"\",\"placeholder\":\"新品有效期\",\"value\":\"\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\",\"titleRatio\":\"\"},{\"_ide_propertytypecode\":\"18\",\"code\":\"896938753782845520\",\"hidden\":\"\",\"_ide_name\":\"status\",\"titleflex\":\"\",\"type\":\"dynamictext\",\"title\":\"状态\",\"_ide_componenttype\":\"weblist-dynamictext\",\"required\":\"\",\"readonly\":\"\",\"flex\":\"\",\"titlewidth\":\"\",\"marktype\":\"\",\"width\":\"200\",\"options\":[{\"color\":\"\",\"icon\":\"\",\"text\":\"启用\",\"key\":\"1\"},{\"color\":\"\",\"icon\":\"\",\"text\":\"停用\",\"key\":\"0\"}],\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"}],\"eventlist\":[],\"code\":\"ctrl_table_row\",\"marktype\":\"\",\"width\":\"\",\"type\":\"row\",\"title\":\"表格列表行定义\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"},\"title\":\"表格列表\",\"_type_ctrlarea\":\"list_area\",\"status\":\"1\"}]}},\"presenter\":{\"initial\":[],\"handlers\":[{\"condition\":\"\",\"code\":\"handler_loadlistdata\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"897025104641396830\",\"getter\":[{\"datatype\":\"0\",\"ctrl\":{\"code\":\"ctrl_table_filter\",\"scope\":\"\"},\"name\":\"kx_kq_product\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845539\"},\"name\":\"productcode\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845538\"},\"name\":\"productname\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896981458235822157\"},\"name\":\"productbrand\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896981458235822156\"},\"name\":\"productcategory\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"distributionbarcode\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896981458235822155\"},\"name\":\"iskeyproduct\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845536\"},\"name\":\"validityperiod\",\"value\":\"\"}]}],\"logiccode\":\"894807236843540572\",\"type\":\"datarequest\",\"setter\":[{\"datatype\":\"1\",\"name\":\"kx_kq_product\",\"ctrlcode\":\"ctrl_table\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"id\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845535\"},\"name\":\"productcode\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845534\"},\"name\":\"productname\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"shortname\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"productbrand\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896993702487134307\"},\"name\":\"productbrand__dicvalue\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"productcategory\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896993702487134306\"},\"name\":\"productcategory__dicvalue\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"distributionunit\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845530\"},\"name\":\"distributionunit__dicvalue\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"singleunit\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845529\"},\"name\":\"singleunit__dicvalue\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"boxweight\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845531\"},\"name\":\"unitconverrate\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"distributionnorm\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"singlenorm\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"boxfactor\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"distributionbarcode\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"singlebarcode\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"retailprice\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"productpackag\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"shelflife\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845522\"},\"name\":\"iskeyproduct\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845521\"},\"name\":\"validityperiod\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845520\"},\"name\":\"status\"},{\"ctrl\":{\"component\":\"\",\"code\":\"896938753782845519\"},\"name\":\"productimage\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"seq\"}]}],\"queue\":\"0\",\"desc\":\"获取数据\"}],\"key\":\"\",\"desc\":\"获取列表数据\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"handler_del\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"897042402362134614\",\"getter\":[{\"datatype\":\"1\",\"ctrl\":{\"code\":\"ctrl_table\",\"scope\":\"checked\"},\"name\":\"kx_kq_product\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"id\",\"value\":\"\"}]}],\"logiccode\":\"895116178861723745\",\"type\":\"datasubmit\",\"setter\":[],\"queue\":\"0\",\"desc\":\"删除-提交\"},{\"handler\":\"handler_refreshlist\",\"code\":\"897042402362134613\",\"type\":\"eventlink\",\"desc\":\"刷新列表\"}],\"key\":\"\",\"desc\":\"删除\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"handler_enable\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"897266467672821763\",\"getter\":[{\"datatype\":\"1\",\"ctrl\":{\"code\":\"ctrl_table\",\"scope\":\"checked\"},\"name\":\"kx_kq_product\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"id\",\"value\":\"\"},{\"ctrl\":{\"component\":\"text\",\"code\":\"\"},\"name\":\"status\",\"value\":\"1\"}]}],\"logiccode\":\"895116178861723742\",\"type\":\"datasubmit\",\"setter\":[],\"queue\":\"0\",\"desc\":\"启用-提交\"},{\"handler\":\"handler_refreshlist\",\"code\":\"897266467672821762\",\"type\":\"eventlink\",\"desc\":\"刷新列表\"}],\"key\":\"\",\"desc\":\"启用\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"handler_disable\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"897266467672821761\",\"getter\":[{\"datatype\":\"1\",\"ctrl\":{\"code\":\"ctrl_table\",\"scope\":\"checked\"},\"name\":\"kx_kq_product\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"id\",\"value\":\"\"},{\"ctrl\":{\"component\":\"text\",\"code\":\"\"},\"name\":\"status\",\"value\":\"0\"}]}],\"logiccode\":\"895116178861723742\",\"type\":\"datasubmit\",\"setter\":[],\"queue\":\"0\",\"desc\":\"停用-提交\"},{\"handler\":\"handler_refreshlist\",\"code\":\"897266467672821760\",\"type\":\"eventlink\",\"desc\":\"刷新列表\"}],\"key\":\"\",\"desc\":\"停用\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"handler_new\",\"actions\":[{\"mode\":\"popup\",\"code\":\"896981458235822154\",\"param\":{\"datatype\":\"0\",\"ctrl\":{\"code\":\"\",\"scope\":\"\"},\"name\":\"__linkparam\",\"properties\":[{\"paramtype\":\"1\",\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"__pagestatus\",\"value\":\"1\"}]},\"type\":\"link\",\"pagecode\":\"896981458235822158\",\"desc\":\"新增-链接\"}],\"key\":\"\",\"desc\":\"新增\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"handler_linkdetail\",\"actions\":[{\"mode\":\"popup\",\"code\":\"897266467672821768\",\"param\":{\"datatype\":\"0\",\"ctrl\":{\"code\":\"\",\"scope\":\"\"},\"name\":\"__linkparam\",\"properties\":[{\"paramtype\":\"1\",\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"__pagestatus\",\"value\":\"2\"},{\"paramtype\":\"2\",\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"id\",\"value\":\"\"}]},\"type\":\"link\",\"pagecode\":\"896981458235822158\",\"desc\":\"编辑-链接\"}],\"key\":\"\",\"desc\":\"链接详情页面\",\"notice\":\"\"},{\"code\":\"handler_refreshlist\",\"actions\":[{\"code\":\"handler_refreshlist_action\",\"ctrlcode\":\"ctrl_table\",\"trigger\":\"onload\",\"type\":\"ctrlevent\",\"desc\":\"刷新列表\"}],\"desc\":\"列表刷新触发事件\",\"notice\":\"\"},{\"code\":\"handler_checkedchanged\",\"actions\":[{\"code\":\"attributeevaluate_action_del\",\"expression\":\"le:{__eventparam.checkednumber}==0\",\"ctrlcode\":\"ctrl_table_button_del\",\"attribute\":\"disabled\",\"type\":\"attributeevaluate\",\"desc\":\"删除按钮属性控制\"},{\"code\":\"attributeevaluate_action_enable\",\"expression\":\"le:{__eventparam.checkednumber}==0\",\"ctrlcode\":\"ctrl_table_button_enable\",\"attribute\":\"disabled\",\"type\":\"attributeevaluate\",\"desc\":\"启用按钮属性控制\"},{\"code\":\"attributeevaluate_action_disable\",\"expression\":\"le:{__eventparam.checkednumber}==0\",\"ctrlcode\":\"ctrl_table_button_disable\",\"attribute\":\"disabled\",\"type\":\"attributeevaluate\",\"desc\":\"停用按钮属性控制\"}],\"desc\":\"列表勾选变更事件\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"896981458235822151\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"896981458235822148\",\"getter\":[{\"datatype\":\"0\",\"ctrl\":{\"code\":\"\",\"scope\":\"\"},\"name\":\"pl_dictionary\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"dictionarycode\",\"value\":\"893288512944738381\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"parentdickey\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"status\",\"value\":\"\"}]}],\"_type_spe\":\"controlbind\",\"logiccode\":\"100000000001100001\",\"type\":\"datarequest\",\"setter\":[{\"datatype\":\"1\",\"name\":\"pl_dictionary\",\"ctrlcode\":\"896981458235822157\",\"properties\":[{\"ctrl\":{\"component\":\"key\",\"code\":\"\"},\"name\":\"dickey\"},{\"ctrl\":{\"component\":\"text\",\"code\":\"\"},\"name\":\"dicvalue\"}]}],\"queue\":\"0\",\"desc\":\"品牌绑定数据\"}],\"key\":\"\",\"desc\":\"品牌绑定数据\",\"notice\":\"\"},{\"condition\":\"\",\"code\":\"896981458235822149\",\"actions\":[{\"mode\":\"1\",\"condition\":\"\",\"code\":\"896981458235822147\",\"getter\":[{\"datatype\":\"0\",\"ctrl\":{\"code\":\"\",\"scope\":\"\"},\"name\":\"pl_dictionary\",\"properties\":[{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"dictionarycode\",\"value\":\"893288512944738380\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"parentdickey\",\"value\":\"\"},{\"ctrl\":{\"component\":\"\",\"code\":\"\"},\"name\":\"status\",\"value\":\"\"}]}],\"_type_spe\":\"controlbind\",\"logiccode\":\"100000000001100001\",\"type\":\"datarequest\",\"setter\":[{\"datatype\":\"1\",\"name\":\"pl_dictionary\",\"ctrlcode\":\"896981458235822156\",\"properties\":[{\"ctrl\":{\"component\":\"key\",\"code\":\"\"},\"name\":\"dickey\"},{\"ctrl\":{\"component\":\"text\",\"code\":\"\"},\"name\":\"dicvalue\"}]}],\"queue\":\"0\",\"desc\":\"品类绑定数据\"}],\"key\":\"\",\"desc\":\"品类绑定数据\",\"notice\":\"\"}],\"interface\":[{\"code\":\"interface_refreshlist\",\"actions\":[{\"handler\":\"handler_refreshlist\",\"code\":\"interface_refreshlist_action\",\"type\":\"eventlink\",\"desc\":\"刷新列表\"}],\"key\":\"refreshlist\",\"desc\":\"刷新列表监听事件\"}]},\"businessmodel\":[{\"actiontype\":\"1\",\"modellogicname\":\"产品列表查询\",\"output\":[{\"objectcode\":\"893288512944738326\",\"datatype\":\"1\",\"name\":\"kx_kq_product\",\"objectname\":\"KX_产品\",\"properties\":[{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品id\",\"propertytypecode\":\"1\",\"name\":\"id\",\"propertycode\":\"893288512944738325\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品编号\",\"propertytypecode\":\"2\",\"name\":\"productcode\",\"propertycode\":\"893288512944738324\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品名称\",\"propertytypecode\":\"2\",\"name\":\"productname\",\"propertycode\":\"893288512944738323\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品简称\",\"propertytypecode\":\"3\",\"name\":\"shortname\",\"propertycode\":\"893288512944738322\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品品牌\",\"propertytypecode\":\"10000000000\",\"name\":\"productbrand\",\"propertycode\":\"893288512944738321\"},{\"objectcode\":\"893288512944738381\",\"propertyname\":\"字典值\",\"propertytypecode\":\"3\",\"name\":\"productbrand__dicvalue\",\"propertycode\":\"893300677118398469\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品品类\",\"propertytypecode\":\"10000000000\",\"name\":\"productcategory\",\"propertycode\":\"893288512944738320\"},{\"objectcode\":\"893288512944738380\",\"propertyname\":\"字典值\",\"propertytypecode\":\"3\",\"name\":\"productcategory__dicvalue\",\"propertycode\":\"893300868403826693\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"分销单位\",\"propertytypecode\":\"10000000000\",\"name\":\"distributionunit\",\"propertycode\":\"893288512944738319\"},{\"objectcode\":\"893405830819483747\",\"propertyname\":\"字典值\",\"propertytypecode\":\"3\",\"name\":\"distributionunit__dicvalue\",\"propertycode\":\"893406252552556549\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"单品单位\",\"propertytypecode\":\"10000000000\",\"name\":\"singleunit\",\"propertycode\":\"893288512944738318\"},{\"objectcode\":\"893288512944738304\",\"propertyname\":\"字典值\",\"propertytypecode\":\"3\",\"name\":\"singleunit__dicvalue\",\"propertycode\":\"893405942023065605\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"箱重\",\"propertytypecode\":\"3\",\"name\":\"boxweight\",\"propertycode\":\"893288512944738317\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"单位转换率\",\"propertytypecode\":\"3\",\"name\":\"unitconverrate\",\"propertycode\":\"893288512944738316\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"分销规格\",\"propertytypecode\":\"3\",\"name\":\"distributionnorm\",\"propertycode\":\"893288512944738315\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"单品规格\",\"propertytypecode\":\"3\",\"name\":\"singlenorm\",\"propertycode\":\"893288512944738314\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"标箱系数\",\"propertytypecode\":\"3\",\"name\":\"boxfactor\",\"propertycode\":\"893288512944738313\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"分销条码\",\"propertytypecode\":\"3\",\"name\":\"distributionbarcode\",\"propertycode\":\"893288512944738312\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"单品条码\",\"propertytypecode\":\"3\",\"name\":\"singlebarcode\",\"propertycode\":\"893288512944738311\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"建议零售价\",\"propertytypecode\":\"3\",\"name\":\"retailprice\",\"propertycode\":\"893288512944738310\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品包装\",\"propertytypecode\":\"3\",\"name\":\"productpackag\",\"propertycode\":\"893288512944738309\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"保质时长\",\"propertytypecode\":\"5\",\"name\":\"shelflife\",\"propertycode\":\"893288512944738308\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"是否重点产品\",\"propertytypecode\":\"18\",\"name\":\"iskeyproduct\",\"propertycode\":\"893288512944738307\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"新品有效期\",\"propertytypecode\":\"7\",\"name\":\"validityperiod\",\"propertycode\":\"893288512944738306\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"状态\",\"propertytypecode\":\"18\",\"name\":\"status\",\"propertycode\":\"893288512944738305\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品图片\",\"propertytypecode\":\"16\",\"name\":\"productimage\",\"propertycode\":\"895491165871280227\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"序号\",\"propertytypecode\":\"6\",\"name\":\"seq\",\"propertycode\":\"896252769617449050\"}]}],\"input\":[{\"objectcode\":\"893288512944738326\",\"datatype\":\"0\",\"name\":\"kx_kq_product\",\"objectname\":\"KX_产品\",\"properties\":[{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品编号\",\"propertytypecode\":\"2\",\"name\":\"productcode\",\"propertycode\":\"893288512944738324\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品名称\",\"propertytypecode\":\"2\",\"name\":\"productname\",\"propertycode\":\"893288512944738323\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品品牌\",\"propertytypecode\":\"10000000000\",\"name\":\"productbrand\",\"propertycode\":\"893288512944738321\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品品类\",\"propertytypecode\":\"10000000000\",\"name\":\"productcategory\",\"propertycode\":\"893288512944738320\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"是否重点产品\",\"propertytypecode\":\"18\",\"name\":\"iskeyproduct\",\"propertycode\":\"893288512944738307\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"新品有效期\",\"propertytypecode\":\"7\",\"name\":\"validityperiod\",\"propertycode\":\"893288512944738306\"}]}],\"execmode\":\"1\",\"actioncategory\":\"1\",\"marktype\":\"1\",\"modelcode\":\"894807236843540573\",\"modellogiccode\":\"894807236843540572\",\"usedatarule\":\"1\",\"status\":1},{\"actiontype\":\"1\",\"modellogicname\":\"字典应用查询\",\"output\":[{\"objectcode\":\"829602839767532638\",\"datatype\":\"1\",\"name\":\"pl_dictionary\",\"properties\":[{\"objectcode\":\"829602839767532638\",\"propertyname\":\"字典\",\"propertytypecode\":\"6\",\"name\":\"dictionaryid\",\"statfunc\":\"\",\"propertycode\":\"829609839767536111\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"父字典\",\"propertytypecode\":\"1000000000\",\"name\":\"parentdictionaryid\",\"statfunc\":\"\",\"propertycode\":\"829609839767536112\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"字典标识编码\",\"propertytypecode\":\"6\",\"name\":\"dictionarycode\",\"statfunc\":\"\",\"propertycode\":\"829609839767536113\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"字典key\",\"propertytypecode\":\"1\",\"name\":\"dickey\",\"statfunc\":\"\",\"propertycode\":\"829609839767536114\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"对接字典编码\",\"propertytypecode\":\"3\",\"name\":\"dickeycode\",\"statfunc\":\"\",\"propertycode\":\"829609839767536119\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"字典值\",\"propertytypecode\":\"3\",\"name\":\"dicvalue\",\"statfunc\":\"\",\"propertycode\":\"829609839767536115\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"key路径值\",\"propertytypecode\":\"4\",\"name\":\"keypath\",\"statfunc\":\"\",\"propertycode\":\"829609839767536120\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"name路径值\",\"propertytypecode\":\"4\",\"name\":\"namepath\",\"statfunc\":\"\",\"propertycode\":\"829609839767536130\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"层级\",\"propertytypecode\":\"5\",\"name\":\"level\",\"statfunc\":\"\",\"propertycode\":\"829629839767537131\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"顺序\",\"propertytypecode\":\"20\",\"name\":\"seq\",\"statfunc\":\"\",\"propertycode\":\"829609839767536124\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"状态\",\"propertytypecode\":\"18\",\"name\":\"status\",\"statfunc\":\"\",\"propertycode\":\"829609839767536125\"}]}],\"input\":[{\"objectcode\":\"829602839767532638\",\"datatype\":\"0\",\"name\":\"pl_dictionary\",\"objectname\":\"业务字典\",\"properties\":[{\"objectcode\":\"829602839767532638\",\"propertyname\":\"字典标识编码\",\"propertytypecode\":\"6\",\"name\":\"dictionarycode\",\"propertycode\":\"829609839767536113\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"关联父字典key\",\"propertytypecode\":\"6\",\"name\":\"parentdickey\",\"propertycode\":\"829609839767536230\"},{\"objectcode\":\"829602839767532638\",\"propertyname\":\"状态\",\"propertytypecode\":\"18\",\"name\":\"status\",\"propertycode\":\"829609839767536125\"}]}],\"execmode\":\"1\",\"actioncategory\":\"1\",\"marktype\":\"1\",\"modelcode\":\"100000000000000001\",\"modellogiccode\":\"100000000001100001\",\"usedatarule\":\"1\",\"status\":1},{\"actiontype\":\"1\",\"modellogicname\":\"产品启用停用\",\"output\":[],\"input\":[{\"objectcode\":\"893288512944738326\",\"datatype\":\"1\",\"name\":\"kx_kq_product\",\"objectname\":\"KX_产品\",\"properties\":[{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品id\",\"propertytypecode\":\"1\",\"name\":\"id\",\"propertycode\":\"893288512944738325\"},{\"objectcode\":\"893288512944738326\",\"propertyname\":\"状态\",\"propertytypecode\":\"18\",\"name\":\"status\",\"propertycode\":\"893288512944738305\"}]}],\"execmode\":\"1\",\"actioncategory\":\"1\",\"marktype\":\"1\",\"modelcode\":\"894807236843540573\",\"modellogiccode\":\"895116178861723742\",\"usedatarule\":\"1\",\"status\":1},{\"actiontype\":\"1\",\"modellogicname\":\"删除产品\",\"output\":[],\"input\":[{\"objectcode\":\"893288512944738326\",\"datatype\":\"1\",\"name\":\"kx_kq_product\",\"objectname\":\"KX_产品\",\"properties\":[{\"objectcode\":\"893288512944738326\",\"propertyname\":\"产品id\",\"propertytypecode\":\"1\",\"name\":\"id\",\"propertycode\":\"893288512944738325\"}]}],\"execmode\":\"1\",\"actioncategory\":\"1\",\"marktype\":\"1\",\"modelcode\":\"894807236843540573\",\"modellogiccode\":\"895116178861723745\",\"usedatarule\":\"1\",\"status\":1}],\"pageinfo\":{\"ref\":\"\",\"eventlist\":[{\"handler\":\"\",\"trigger\":\"onload\"},{\"handler\":\"\",\"trigger\":\"onunload\"}],\"code\":\"896938753782845517\",\"clientcategorycode\":\"1\",\"directorytypecode\":\"30001\",\"marktype\":\"\",\"pagetemplatecode\":\"1\",\"title\":\"产品管理页面\",\"type\":\"5\",\"status\":\"1\"}}";
        String json4_3 = getDiffJson(json3, json4, "").toString();
    //    System.out.println("json4-json3:" + json4_3);
    //    System.out.println("json4:" + getMergeJson(json3, json4_3, ""));

        //System.out.println(filter(new JSONObject(json2),"a","b"));
        System.out.println(filter(new JSONObject(page),"title","placehloder","text","desc"));

        /*System.out.println("json1：" + json1.toString());
        System.out.println("json2：" + json2.toString());
        try {
            String d = getDiffJson(json2, json1, "code,c-code,menucode,datarightcode,functioncode,fieldrightcode,rolecode").toString();
            //    String d = getDiffJson(json2, json1, ".*code").toString();
            System.out.println("差量json：" + d);
            String un = getMergeJson(json2, d, "code,c-code,menucode,datarightcode,functioncode,fieldrightcode,rolecode").toString();
            System.out.println("合并后：" + un);

            System.out.println("-------------------------------------");

            System.out.println(getMergeJson("{}", "{\"name\":12}", "code"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 比较两个json数据的方法，如果json里面有数组，根据关键字field去判断，以什么为基准去比较
     *
     * @param srcJson 待比较的json中数据少的
     * @param json    待比较的json中数据多的
     * @param field   数组要进行比较的基准属性，多个属性用英文逗号分隔
     * @return
     */
    public static JSONObject getDiffJson(JSONObject srcJson, JSONObject json, String field) {
        compareJson(json, srcJson, null, field);
        return json;
    }

    /**
     * 合并两个json，如果json里面有数组，根据关键字field去判断，以什么为基准去比较合并
     *
     * @param srcJson 源json数据
     * @param json    差量json数据
     * @param field   数组要进行比较合并的基准属性，多个属性用英文逗号分隔
     * @return
     */
    public static JSONObject getMergeJson(JSONObject srcJson, JSONObject json, String field) throws Exception {
        JSONObject src = new JSONObject(srcJson.toString());
        mergeJson(srcJson, json, src, null, field);
        return src;
    }

    public static JSONObject getDiffJson(String srcJson, String json, String field) throws Exception {
        srcJson = StringUtils.isEmpty(srcJson) ? "{}" : srcJson;
        json = StringUtils.isEmpty(json) ? "{}" : json;
        JSONObject json1 = new JSONObject(json);
        JSONObject json2 = new JSONObject(srcJson);
        compareJson(json1, json2, null, field);
        return json1;
    }

    public static JSONObject getMergeJson(String srcJson, String json, String field) throws Exception {
        srcJson = StringUtils.isEmpty(srcJson) ? "{}" : srcJson;
        json = StringUtils.isEmpty(json) ? "{}" : json;
        JSONObject json1 = new JSONObject(srcJson);
        JSONObject json2 = new JSONObject(json);
        JSONObject src = new JSONObject(srcJson);
        mergeJson(json1, json2, src, null, field);
        return src;
    }

    /**
     * 比较两个json，产生差量json数据
     *
     * @param json1 长json
     * @param json2 短json
     * @param key   json的key，如果是最外面那一层，则key为null
     * @param field 需要被保留的关键字列表，多个用逗号分隔
     * @return
     */
    private static boolean compareJson(JSONObject json1, JSONObject json2, String key, String field) {

        Iterator<String> i = json1.keys();
        List<String> remove_keys = new ArrayList<String>();
        List<String> fields = Arrays.asList(field.split(","));
        boolean flag = true;
        //遍历长的那个json
        while (i.hasNext()) {
            key = i.next();
            //将长的json里面的每个key的值和短的json中每个对应key的值进行比较
            boolean b = compareJson(json1.opt(key), json2.opt(key), key, field);
            //如果相同，则标记删除该key
            if (b) {
                remove_keys.add(key);
            }
            //只有当当前层中所有key的值都相同最终结果才是相同
            flag = flag && b;
        }
        //循环所有被标记删除的key
        for (String str : remove_keys) {
            /**
             * 如果该key不在指定保留的关键字列表中或者是该层中所有key值都是一样，说明两个json中该块
             * json完全相同，则删掉长json中相同的块
             */
            if (!fields.contains(str) || flag) {
                json1.remove(str);
                //  json2.remove(str);
            }
        }
        return flag;

    }

    /**
     * 将差量json合并到源json中去
     *
     * @param json1 源json
     * @param json2 差量json
     * @param json  源json的备份json，防止直接对源json的增加修改照成递归迭代
     * @param key   json的key，如果是最外面那一层，则key为null
     * @param field 在json数组中需要对应比较的关键字列表
     * @return
     * @throws Exception
     */
    private static boolean mergeJson(JSONObject json1, JSONObject json2, JSONObject json, String key, String field) throws Exception {

        Iterator<String> i = json2.keys();
        boolean flag = true;
        //迭代插件json的key
        while (i.hasNext()) {
            key = i.next();
            //拿到源json中每个key的值
            Object obj1 = json1 == null ? null : json1.opt(key);
            //拿到差量json中对应key的值
            Object obj2 = json2 == null ? null : json2.opt(key);
            //拿到备份源json的对应key的值
            Object obj = json == null ? null : json.opt(key);
            boolean b = false;
            /**
             * 如果差量json中该key对应的值不为空，并且源json中对应字段的值不为空，
             * 则直接把该键值对放入备份json中
             */
            if (obj2 != null && obj1 == null && json != null) {
                json.put(key, obj2);
            } else {
                //否则合并对应key的值，并且返回两个值是否相同
                b = mergeJson(obj1, obj2, obj, key, field);
                /**
                 * 如果对应key的两个值不同，并且差量json中对应key的值不为空，并且源json中该key的值是json中的叶子节点了
                 * 直接把差量中该键值对放入备份json中
                 */
                if (!b && json != null && obj2 != null && obj1 != null && !(obj1 instanceof JSONObject) && !(obj1 instanceof JSONArray)) {
                    json.put(key, obj2);
                }
            }

            flag = flag && b;
        }
        return flag;

    }

    /**
     * 比较两个json对象里某个key的值是否相同
     *
     * @param json1 长json里的某个key的值
     * @param json2 短json里的某个key的值
     * @param key   json中的key值
     * @param field 需要保留的关键字列表，多个用逗号分隔
     * @return
     */
    private static boolean compareJson(Object json1, Object json2, String key, String field) {

        //如果两个值都为null则返回true
        if (json2 == null || json1 == null) {
            return json2 == json1;
        }
        //如果长json的key的值是一个JSON对象则递归调用json对象的比较方法
        if (json1 instanceof JSONObject) {
            return compareJson((JSONObject) json1, (JSONObject) json2, key, field);
        } else if (json1 instanceof JSONArray) {
            //如果长json的key的值是一个JSON数组，则递归调用json数组的比较方法
            return compareJson((JSONArray) json1, (JSONArray) json2, key, field);
        } else { //其它情况都调用字符串的比较方法
            return compareJson(json1.toString(), json2.toString(), key, field);
        }

    }

    /**
     * 合并两个json中指定key的值，并且返回两个对象是否相同，相同则不需要合并，不同则可以合并
     *
     * @param json1 源json中指定key的值
     * @param json2 差量json中指定key的值
     * @param json  备份对象中指定key的值
     * @param key   指定的key
     * @param field 用来匹配两个json数组中相对应的元素的关键字列表，多个用逗号隔开
     * @return
     * @throws Exception
     */
    private static boolean mergeJson(Object json1, Object json2, Object json, String key, String field) throws Exception {

        //如果差量json中该key的值为null则直接判断两个值是否相等
        if (json2 == null) {
            return json2 == json1;
        }
        //如果源json中该key的值为null则直接返回false，表示两个值不相等可以合并
        if (json1 == null) {
            return false;
        }
        //如果差量json中该key的值是一个json对象则递归调用json对象的合并方法
        if (json2 instanceof JSONObject) {
            return mergeJson((JSONObject) json1, (JSONObject) json2, (JSONObject) json, key, field);
        } else if (json2 instanceof JSONArray) {
            //如果差量json中该key的值是一个json数组，则调用json数组的合并方法
            return mergeJson((JSONArray) json1, (JSONArray) json2, (JSONArray) json, key, field);
        } else {
            return mergeJson(json1.toString(), json2.toString(), key, field);
        }

    }

    private static boolean compareJson(String str1, String str2, String key, String field) {

        return str1.equals(str2);

    }

    private static boolean mergeJson(String str1, String str2, String key, String field) {

        return str1.equals(str2);

    }

    /**
     * 比较两个json中key的值是数组的方法
     *
     * @param json1 长json中key的值
     * @param json2 短json中key的值
     * @param key   该key值
     * @param field 需要保留的关键字列表
     * @return
     */
    private static boolean compareJson(JSONArray json1, JSONArray json2, String key, String field) {

        //先定义一个标志
        boolean flag = true;
        //定义一个用来存放json数组中需要删除的索引位置
        List<Integer> idxs = new ArrayList<Integer>();
        //遍历长json中key值的json数组
        for (int i = 0; i < json1.length(); i++) {
            //依次拿出每一个索引位置的值
            JSONObject obj1 = json1.optJSONObject(i);
            boolean b = false;
            if (obj1 == null) { //如果json数组只是一般数据类型
                Object o1 = json1.opt(i);
                Object o2 = getObjByObj(o1, json2);
                if (o1 == o2 || (o1 != null && o1.equals(o2))) {
                    b = true;
                }
            } else {
                //在第二个json中的对应数组中找到关键字相匹配的值，有可能两个json数组某些值在数组中位置不同
                JSONObject obj2 = getJSONObjectByField(obj1, json2, field);
                //比较两个对应的索引位置的值
                b = obj2 == null ? false : compareJson(obj1, obj2, key, field);
            }

            //如果一样则加入删除列表
            if (b) {
                idxs.add(i);
            }
            //更新标志，只有当每组都相等，最终才会为true
            flag = flag && b;
        }
        //循环被标记删除的索引列表
        for (int i = 0; i < idxs.size(); i++) {
            //每当被删除后，集合会向前移动，所以导致实际上应该删除的索引位置会向前移动一位
            int idx = idxs.get(i) - i;
            json1.remove(idx);
            //       json2.remove(idx);
        }
        return flag;

    }


    /**
     * 合并两个json中指定key的值是数组的方法
     *
     * @param json1 源json数组中指定key的值
     * @param json2 差量数组中指定key的值
     * @param json  备份数组中指定key的值
     * @param key   指定key
     * @param field 用来匹配两个json数组中相对应的元素的关键字列表，多个用逗号隔开
     * @return
     * @throws Exception
     */
    private static boolean mergeJson(JSONArray json1, JSONArray json2, JSONArray json, String key, String field) throws Exception {
        //定义一个标识是否需要合并的标记变量
        boolean flag = true;
        //循环差量json中的json数组
        for (int i = 0; i < json2.length(); i++) {
            //拿到每一个json数组中的元素
            JSONObject obj2 = json2.optJSONObject(i);
            boolean b = false;
            if (obj2 == null) { //如果json数组只是一般数据类型
                Object o2 = json2.opt(i);
                Object o1 = getObjByObj(o2, json1);
                Object o = getObjByObj(o2, json);
                if (o1 == o2 || (o2 != null && o2.equals(o1))) {
                    b = true;
                }
                if (!b && o == null) {
                    json.put(json2.opt(i));
                }
            } else {
                //根据关键字查找到源json中的json数组中对应的元素
                JSONObject obj1 = getJSONObjectByField(obj2, json1, field);
                //根据关键字查找到备份json中json数组中对应的元素
                JSONObject obj = getJSONObjectByField(obj2, json, field);
                //合并从源数组中找到的元素和对应的差量数组中的元素
                b = mergeJson(obj1, obj2, obj, key, field);
                //如果两个元素不相同可合并，并且备份数组中找不到该元素，直接put到备份数组中
                if (!b && obj == null && json != null) {
                    json.put(json2.opt(i));
                }
            }


            flag = flag && b;
        }
        return flag;
    }

    /**
     * 根据关键字列表在json数组中找到和指定json对象相对应的json对象
     *
     * @param target 指定的json对象
     * @param json1  json数组
     * @param field  关键字列表
     * @return
     */
    private static JSONObject getJSONObjectByField(JSONObject target, JSONArray json1, String field) {
        field = field == null ? "" : field;
        List<String> fields = Arrays.asList(field.split(","));
        for (int i = 0; json1 != null && i < json1.length(); i++) {
            JSONObject obj = json1.optJSONObject(i);
            String key = fields.get(0);
            if (target != null) {
                for (String f : fields) {
                    if (target.has(f)) {
                        key = f;
                        break;
                    }
                }

            }
            if (target != null && target.opt(key) != null && target.opt(key).equals(obj.opt(key))) {
                return obj;
            }
        }
        return null;
    }

    private static Object getObjByObj(Object target, JSONArray json1) {

        for (int i = 0; json1 != null && i < json1.length(); i++) {
            Object obj = json1.opt(i);
            if (target != null && (target == obj || target.equals(obj))) {
                return obj;
            }
        }
        return null;

    }

    /**
     * 将对象序列化成json
     *
     * @param obj 待序列化的对象
     * @return
     * @throws Exception
     */
    public static String serialize(Object obj) throws Exception {

        if (obj == null) {
            throw new IllegalArgumentException("obj should not be null");
        }
        return mapper.writeValueAsString(obj);
    }

    public static String serialize(Object obj, String[] excludes) throws Exception {
        if (obj == null) {
            throw new IllegalArgumentException("obj should not be null");
        }
        JSONObject json = new JSONObject(mapper.writeValueAsString(obj));
        for (int i = 0; excludes != null && i < excludes.length; i++) {
            json.remove(excludes[i]);
        }
        return json.toString();
    }

    public static <T> T deserialize(String jsonStr, Class<?> collectionClass,
                                    Class<?>... elementClasses) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructParametrizedType(
                collectionClass, collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }

    public static Set<String> filter(JSONObject json,String... keys) {
        Set<String> datas = new HashSet<>();
        List<String> keyList = Arrays.asList(keys);
        objFilter(datas,json,keyList);
        return datas;
    }

    private static void otherFilter(Set<String> datas,String key,Object value,List<String> keyList) {

        if (keyList.contains(key)) {
            if(StringUtils.isNotEmpty(String.valueOf(value))) {
                datas.add(String.valueOf(value));
            }
        }

    }

    private static void objFilter(Set<String> datas,JSONObject json,List<String> keyList) {
        Iterator<String> i = json.keys();
        while(i.hasNext()) {
            String key = i.next();
            Object value = json.opt(key);
            if(value instanceof JSONObject) {
                objFilter(datas,(JSONObject)value,keyList);
            } else if(value instanceof JSONArray) {
                arrayFilter(datas,(JSONArray)value,keyList);
            } else {
                otherFilter(datas,key,value, keyList);
            }

        }
    }

    private static void arrayFilter(Set<String> datas,JSONArray array,List<String> keyList) {

        for(int i = 0; array != null && i < array.length();i++) {
            Object value = array.get(i);
            if(value instanceof JSONObject) {
                objFilter(datas,(JSONObject)value,keyList);
            } else {
                otherFilter(datas,String.valueOf(i),value,keyList);
            }
        }

    }

    /**
     * 将json字符串反序列化成对象
     * @param src 待反序列化的json字符串
     * @param t   反序列化成为的对象的class类型
     * @return
     * @throws Exception
     */
    public static <T> T deserialize(String src, Class<T> t) throws Exception {
        if (src == null) {
            throw new IllegalArgumentException("src should not be null");
        }
        if("{}".equals(src.trim())) {
            return null;
        }
        return mapper.readValue(src, t);
    }

    /**
     * 对json内的jsonobject进行递归排序
    */
    public static JSONObject sortJson(JSONObject jsonObject){
        return (JSONObject) sortJson0(jsonObject);
    }

    // 通用的递归排序函数，适用于JSONObject和JSONArray
    private static Object sortJson0(Object json) {
        if (json instanceof JSONObject) {
            return sortJsonObject((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return sortJsonArray((JSONArray) json);
        } else {
            // 如果是普通值类型，直接返回
            return json;
        }
    }

    // 递归排序JSONObject中的键值对
    private static JSONObject sortJsonObject(JSONObject jsonObject) {
        JSONObject sortedJson = new JSONObject();
        TreeMap<String, Object> sortedMap = new TreeMap<>();

        // 将键值对放入TreeMap，自动按照键的字典顺序排序
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonObject.get(key);
            // 对每个值递归处理
            sortedMap.put(key, sortJson0(value));
        }

        // 将排序后的键值对放入新的JSONObject
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            sortedJson.put(entry.getKey(), entry.getValue());
        }

        return sortedJson;
    }

    // 递归处理JSONArray中的元素
    private static JSONArray sortJsonArray(JSONArray jsonArray) {
        JSONArray sortedArray = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            // 对JSONArray中的每个元素递归处理
            sortedArray.put(sortJson0(value));
        }

        return sortedArray;
    }

}