package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.SocketTimeoutException;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    // Request path: cheese/
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        //show index
        return "cheese/index";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        // Show add
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    // the @ModelAttribute Cheese newCheese part automagicly creates setters for the class
    // based off what comes in
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            // Show add
            return "cheese/add";
        }
        CheeseData.add(newCheese);
        // Redirect to cheese/
        return "redirect:";
    }

    // Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String showRemoveForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        // show remove
        return "cheese/remove";
    }

    // Request path: cheese/remove
    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String removeCheeseForm(@RequestParam int[] cheeseIds) {
        for(int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        // Redirect to cheese/
        return "redirect:";
    }
    // Request path: cheese/edit
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public  String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese getACheese = CheeseData.getById(cheeseId);
        model.addAttribute("cheese", getACheese);
        System.out.print(getACheese);
        // Show cheese/edit
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(int cheeseId, String name, String description) {
        Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        //Added this
        cheeseToEdit.setType(CheeseType.valueOf(name));
        // Redirect to cheese/
        return "redirect:/cheese";
    }
}