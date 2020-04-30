package gui.listener.Category;

import bean.Category;
import bean.Record;
import dao.CategoryDAO;
import dao.RecordDAO;

import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    private RecordDAO recordDAO = new RecordDAO();

    public List<Category> list(){
        List<Category> categories = categoryDAO.list();
        for (Category category : categories){
            List<Record> rs = recordDAO.list(category.getID());
            category.setRecordNumber(rs.size());
        }
        return categories;
    }

    public void add(String name){
        Category newCategory = new Category();
        newCategory.setName(name);
        categoryDAO.add(newCategory);
    }

    public void update(int id, String name){
        Category category = new Category();
        category.setName(name);
        category.setID(id);
        categoryDAO.update(category);
    }

    public void delete(int id){ categoryDAO.delete(id); }

}
