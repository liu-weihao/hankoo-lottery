package com.dx.ss.hankoo.service;

import com.dx.ss.data.doc.ExcelDocument;
import com.dx.ss.data.holder.ExcelDocumentDataHolder;
import com.dx.ss.data.resolver.ExcelDocumentDataImportResolver;
import com.dx.ss.data.util.ExcelDocumentUtil;
import com.dx.ss.hankoo.dal.model.ParticipantModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author Eric Lau
 * @Date 2017/9/11.
 */
public class BizExcelDocumentDataImportResolver extends ExcelDocumentDataImportResolver<ExcelDocumentDataHolder> {

    public ExcelDocumentDataHolder resolveByImport(ExcelDocument excelDocument) {

        ExcelDocumentDataHolder holder = new ExcelDocumentDataHolder();
        try {

            List<ParticipantModel> dataList = new ArrayList<>();
            Workbook workbook = excelDocument.workbook();
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int index = 1; index <= lastRowNum; index++) {
                Row row = sheet.getRow(index);
                ParticipantModel data = new ParticipantModel();
                dataList.add(data);
                ExcelDocumentUtil.copyPropertiesByRow(row, data, data.propertiesAssembly(), 0, null);
            }
            holder.setDataList(dataList);
            holder.setDocumentation(excelDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holder;
    }
}
