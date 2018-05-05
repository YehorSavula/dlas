package ua.com.nure.dlas.services.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.com.nure.dlas.model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProgramParser {

    private static final Logger LOG = Logger.getLogger(ProgramParser.class);

    public List<Course> parseProgram(MultipartFile file) {
        Workbook workbook = getWorkbookFromFile(file);
        if (Objects.isNull(workbook)) {
            return Collections.emptyList();
        }
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        List<Course> parsedCourses = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row: sheet) {
            String firstCellValue = row.getCell(0).getStringCellValue();
            if (firstCellValue.isEmpty()) {
                continue;
            }

            if ("ВСЬОГО:".equals(firstCellValue)) {
                break;
            }

            parsedCourses.add(new Course(row.getCell(1).getStringCellValue(), getHours(row, evaluator),
                    row.getCell(32).getStringCellValue()));
        }
        return parsedCourses;
    }

    private int getHours(Row row, FormulaEvaluator evaluator) {
        Cell cell;
        if (CellType.FORMULA.equals(row.getCell(3).getCellTypeEnum())) {
            cell = row.getCell(3);
        } else {
            cell = row.getCell(17);
        }

        return CellType.FORMULA.equals(cell.getCellTypeEnum()) ?
                new Double(evaluator.evaluate(cell).getNumberValue()).intValue() : 0;
    }

    private Workbook getWorkbookFromFile(MultipartFile file) {
        try {
            if ("xlsx".equals(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                return new XSSFWorkbook(file.getInputStream());
            }
            return new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            LOG.error("Can't open file: " + file.getOriginalFilename());
        }
        return null;
    }
}
