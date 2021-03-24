package easymall.po;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelFileGenerator {

    private final int SPLIT_COUNT = 1500; // Excel每个工作簿的行数

    private ArrayList<String> fieldName = null; // excel标题数据集(ArrayList里存放的是字符串)

    private ArrayList<List<String>> fieldData = null; // excel数据内容(ArrayList里存放的是List集合元素)

    private HSSFWorkbook workBook = null;

    /**
     * 构造器
     * 
     * @param fieldName
     *            结果集的字段名
     * @param data
     */
    public ExcelFileGenerator(ArrayList<String> fieldName, ArrayList<List<String>> fieldData) {

        this.fieldName = fieldName;
        this.fieldData = fieldData;
    }

    /**
     * 创建HSSFWorkbook对象
     * 
     * @return HSSFWorkbook
     */
    public HSSFWorkbook createWorkbook() {
    	System.out.println(this.fieldName);
    	System.out.println(this.fieldData);
        workBook = new HSSFWorkbook();
        int rows = fieldData.size();
        int sheetNum = 0;

        if (rows % SPLIT_COUNT == 0) {
            sheetNum = rows / SPLIT_COUNT;
        } else {
            sheetNum = rows / SPLIT_COUNT + 1;
        }

        for (int i = 1; i <= sheetNum; i++) {// 创建sheet
            HSSFSheet sheet = workBook.createSheet("Page " + i);
            HSSFRow headRow = sheet.createRow((short) 0); // 创建Row
            for (int j = 0; j < fieldName.size(); j++) {// 创建Cell
                HSSFCell cell = headRow.createCell((short) j);
                // 添加样式
                cell.setCellType(CellType.NUMERIC);
                // 防止中文高位截断
                if (fieldName.get(j) != null) {
                    cell.setCellValue((String) fieldName.get(j));
                } else {
                    cell.setCellValue("-");
                }
            }

            for (int k = 0; k < (rows < SPLIT_COUNT ? rows : SPLIT_COUNT); k++) {
                HSSFRow row = sheet.createRow((short) (k + 1));
                // 将数据内容放入excel单元格（filedData内存放的为集合元素，所以可以转型为ArrayList）
                ArrayList<String> rowList = (ArrayList<String>) fieldData.get((i - 1) * SPLIT_COUNT + k);
                for (int n = 0; n < rowList.size(); n++) {
                    HSSFCell cell = row.createCell((short) n);
                    if (rowList.get(n) != null) {
                        cell.setCellValue((String) rowList.get(n).toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
        return workBook;
    }

    public void expordExcel(OutputStream os) throws Exception {
        workBook = createWorkbook();
        workBook.write(os);
        os.close();
    }

}
