package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseType;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired
    CheeseDao cheeseDao;

    @Autowired
    CategoryDao categoryDao;


    // Request path: cheese/
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        //show index
        return "cheese/index";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        // Show add
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    // the @ModelAttribute Cheese newCheese part automagicly creates setters for the class
    // based off what comes in
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            // Show add
            return "cheese/add";
        }
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        // Redirect to cheese/
        return "redirect:/cheese";
    }

    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String showRemoveForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeseDao.findAll());
        // show remove
        return "cheese/remove";
    }

    // Request path: cheese/remove
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String removeCheeseForm(@RequestParam int[] ids) {
        for(int id : ids) {
            cheeseDao.delete(id);
        }
        // Redirect to cheese/
        return "redirect:/cheese";
    }

    @RequestMapping(value="category", method = RequestMethod.GET)
    public  String category(Model model, @RequestParam int id) {
        Category cat = categoryDao.findOne(id);
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }


    // Request path: cheese/edit
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public  String displayEditForm(Model model, @PathVariable int id) {
        Cheese getACheese = cheeseDao.findOne(id);
        model.addAttribute("cheese", getACheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        // Show cheese/edit
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{id}", method = RequestMethod.POST)
    public String processEditForm(int id, String name, String description, CheeseType type) {
        Cheese cheeseToEdit = cheeseDao.findOne(id);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        //Added this
        cheeseToEdit.setType(type);
        // Redirect to cheese/
        return "redirect:/cheese";
    }
}