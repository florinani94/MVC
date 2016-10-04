package com.evozon.mvc;

import com.evozon.domain.Category;
import com.evozon.domain.Product;
import com.evozon.mvc.validator.CategoryValidator;
import com.evozon.service.CartService;
import com.evozon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/backoffice/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryValidator validator;

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String showAddCategory(Model model){
        model.addAttribute("category", new Category());
        return "createCategory";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addCategory(Model model, @ModelAttribute("category") Category category, BindingResult result){

        validator.validate(category,result);
        try {
            categoryService.addCategory(category);
            model.addAttribute("result", true);
            if (result.hasErrors()) {
                model.addAttribute("result", false);
                model.addAttribute("category",category);
                return "createCategory";
            }

        }catch (Exception e){
            model.addAttribute("result", false);
            return "createCategory";
        }
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "viewCategories";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String viewCategories(Model model){
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "viewCategories";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteCategory(@RequestParam("id") int id, Model model){
        categoryService.deleteCategory(id);
        model.addAttribute("allCategories", categoryService.getAllCategories());

        return "viewCategories";
    }

    @RequestMapping(value = "/edit/{categoryId}", method = RequestMethod.GET)
    public String goToCategoryEditPage(Model model, @PathVariable("categoryId") int categoryId) {

        model.addAttribute("category", categoryService.getCategoryById(categoryId));

        return "editCategory";
    }

     @RequestMapping(value = "/edit", method = RequestMethod.POST)
     public String editCategory(Model model, @ModelAttribute("category") Category category,BindingResult result,SessionStatus status){

         //validation
         validator.validate(category,result);

         //check validation errors
         if(result.hasErrors()){
             return "editCategory";
         }

         //update category in database
         categoryService.updateCategory(category);

         model.addAttribute("allCategories", categoryService.getAllCategories());
         return "viewCategories";
     }



}
