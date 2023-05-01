package com.example.bmicalculator;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BMIController {
    @GetMapping("/enterinput")
    public ModelAndView enterinput() {
        Input input = new Input();
        ModelAndView mav = new ModelAndView();
        mav.addObject("input", input);
        mav.setViewName("enterinput");
        return mav;
    }
//
//    @GetMapping("/result")
//    public ModelAndView result(@RequestParam(value="weight") double weight,
//                               @RequestParam(value="height") double height,
//                               @RequestParam(value="result") double result,HttpSession session) {
//        Map<String, Input> results = (Map<String, Input>) session.getAttribute("input");
//        if (results == null) {
//            results = new HashMap<>();
//            session.setAttribute("input", results);
//        }
//        Map<String, Object> params = new HashMap<>();
//        params.put("height", height);
//        params.put("weight", weight);
//        params.put("result", result);
//        return new ModelAndView("result", params);
//    }
//
//    @PostMapping("/calculateBmi")
//    public ModelAndView calculateBmi(@Valid @ModelAttribute("input") Input input, BindingResult bindingResult, HttpSession session) {
//        ModelAndView mav = new ModelAndView();
//        if (bindingResult.hasErrors()) {
//            mav.setViewName("enterinput");
//            return mav;
//        }
//
//        Map<String, Object> params = new HashMap<>();
//        if (input != null) {
//            Map<String, Input> inputs = (Map<String, Input>) session.getAttribute("input");
//            if (inputs == null) {
//                inputs = new HashMap<String, Input>();
//                session.setAttribute("input", inputs);
//            }
//            params.put("weight", input.getWeight());
//            params.put("height", input.getHeight());
//            params.put("result", BMICalculator.calculateBmi(input.getWeight(), input.getHeight()));
//
//        }
//        return new ModelAndView("redirect:result", params);
//    }
    @PostMapping("/calculateBmi")
    public String calculateBmi(@Valid @ModelAttribute("input") Input input, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/enterinput";
        }

        if (input != null) {
            session.setAttribute("weight", input.getWeight());
            session.setAttribute("height", input.getHeight());
            session.setAttribute("result", BMICalculator.calculateBmi(input.getWeight(), input.getHeight()));
        }

        return "redirect:/result";
    }

    @GetMapping("/result")
    public ModelAndView result(HttpSession session) {
        Double weight = (Double) session.getAttribute("weight");
        Double height = (Double) session.getAttribute("height");
        Double result = (Double) session.getAttribute("result");

        Map<String, Object> params = new HashMap<>();
        params.put("height", height);
        params.put("weight", weight);
        params.put("result", result);

        return new ModelAndView("result", params);
    }
}
