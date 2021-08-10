package com.soar.spring.jpa.crud.service;

import com.soar.spring.jpa.crud.entity.Guardian;
import com.soar.spring.jpa.crud.entity.Student;
import com.soar.spring.jpa.crud.repository.StudentRepository;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Log
@Data
@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {

    private List<Student> studentListIn = new ArrayList<>();

    @Autowired
    private EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        entityManager.persist(student);
        log.info("Student is saved.");
        return student;
    }

    @Override
    @Transactional
    public Student deleteStudent(Student student) {
        if (entityManager.contains(student)) {
            entityManager.remove(student);
        } else {
            entityManager.remove(entityManager.merge(student));
        }
        return student;
    }

    @Override
    @Transactional
    public Student deleteStudentById(Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);

        return student;
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) {
        Student exist = entityManager.find(Student.class, id);

        exist = this.copyStudents(exist, student);
        //entityManager.merge(student);
        entityManager.flush();

        return exist;
    }

    @Override
    @Transactional
    public Student getStudent(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {

        List<Student> list = studentRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Student findStudentByEmail(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        return student;
    }

    // download excel file
    @Override
    @Transactional
    public ByteArrayInputStream getStudentsToExcel(List<Student> students) throws IOException {

        String[] COLUMNs = {"Student ID:", "First Name:", "Last Name:", "E-mail:", "Guardian Name:", "Guardian E-mail:", "Guardian Mobile:"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Students Report");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(student.getStudentId());
                row.createCell(1).setCellValue(student.getFirstName());
                row.createCell(2).setCellValue(student.getLastName());
                row.createCell(3).setCellValue(student.getEmailId());
                row.createCell(4).setCellValue(student.getGuardian().getName());
                row.createCell(5).setCellValue(student.getGuardian().getEmail());
                row.createCell(6).setCellValue(student.getGuardian().getMobile());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }

    }

    @Override
    @Transactional
    public List<Student> showFiles(FileInputStream io, String fName) {
       //String result = "Reading is done.";
        if(!studentListIn.isEmpty()){
            studentListIn.clear();
        }
            try {
                List<String> listEmails = this.readFromExcel(io, fName);
                //System.out.println("ListEmails=" + listEmails.toString());
                for (String email : listEmails) {
                    email = email.trim();
                    Student student = studentRepository.findStudentByEmail(email);
                    //log.info("Student="+student.toString());
                    if (student != null) {
                        System.out.println("STUDENT:"+student.toString());
                       studentListIn.add(student);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        Collections.sort(studentListIn, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentId().intValue() - o2.getStudentId().intValue();
            }
        });
            return studentListIn;
    }

    private Workbook getWorkbook(FileInputStream inputStream, String exelFilePath) {
        Workbook workbook = null;
        try {
            if (exelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (exelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    public List<String> readFromExcel(FileInputStream io, String fName) {
        Set<String> result = new TreeSet<>();
        List<String> list = new ArrayList<>();

        if (fName != null && !("".equals(fName))) {
            fName = fName.trim();
            try {
                Workbook workbook = getWorkbook(io, fName);
                Sheet datatypeSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = datatypeSheet.iterator();

                while (iterator.hasNext()) {

                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();

                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();
                        String value = currentCell.getStringCellValue();
                        if (value != null && !"".equals(value)) {
                            result.add(value);
                        }

                    }
                }
                log.info("result from excel="+result.size());
                workbook.close();
                list = result.parallelStream().collect(Collectors.toList());
                //log.info("RESULT from EXCEL:"+ list);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //log.info("File not found....");
            } catch (IOException e) {
                e.printStackTrace();
                //log.info("Problem with IO...");
            } catch (EncryptedDocumentException e) {
                e.printStackTrace();
                //log.info("Problem with encrypting document...");
            }

            finally {
                if (io != null) {
                    try {
                        io.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }

    private Student copyStudents(Student orig, Student s){
        orig.setFirstName(s.getFirstName());
        orig.setLastName(s.getLastName());
        //orig.setEmailId(s.getEmailId());
        Guardian g = new Guardian();
        g.setName(s.getGuardian().getName());
        g.setMobile(s.getGuardian().getMobile());
        g.setEmail(s.getGuardian().getEmail());
        orig.setGuardian(g);
        return orig;
    }
}
