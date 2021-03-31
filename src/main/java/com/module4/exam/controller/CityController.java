package com.module4.exam.controller;

import com.module4.exam.model.City;
import com.module4.exam.model.Nation;
import com.module4.exam.service.city.ICityService;
import com.module4.exam.service.nation.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private INationService nationService;

    @ModelAttribute("listNation")
    public Iterable<Nation> showAll(){
        return nationService.showAll();
    }
    //show list
    @GetMapping("")
    public ModelAndView showList(@PageableDefault (size = 3) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("home");
        Page<City> cities = cityService.showAll(pageable);
        modelAndView.addObject("listCity", cities);
        modelAndView.addObject("nation", new Nation());
        return modelAndView;
    }
    //show detail
    @GetMapping("/detail")
    public ModelAndView showDetail(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        City city = cityService.findById(id);
        modelAndView.addObject("city",city);
        return modelAndView;
    }
    //create
    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createCity(@Validated @ModelAttribute City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return new ModelAndView("/create", "city", city);
        } else {
            cityService.save(city);
            return new ModelAndView("redirect:/city");
        }

    }
    //edit
    @GetMapping("/edit")
    public ModelAndView showFormEdit(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        City city = cityService.findById(id);
        modelAndView.addObject("city", city);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam Long id, @ModelAttribute City city){
        city.setId(id);
        cityService.save(city);
        return new ModelAndView("redirect:/city");
    }
    //delete
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam Long id){
        cityService.delete(id);
        return new ModelAndView("redirect:/city");
    }
}
