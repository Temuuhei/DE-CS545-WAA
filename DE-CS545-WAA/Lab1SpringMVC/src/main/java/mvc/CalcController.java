package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CalcController {
    @RequestMapping("/calc")
    public ModelAndView calculator( @RequestParam(value="num1") String number1, @RequestParam(value="op") String op,
                                    @RequestParam(value="num2") String number2) {

        int result = 0;
        int num1 = Integer.parseInt(number1);
        int num2 = Integer.parseInt(number2);
        switch (op) {
            case "*" : result = num1 * num2;
                break;
//           "+" value does not work
//            case "+" : result = num1 + num2;
//                break;
            case "-" : result = num1 - num2;
                break;
            case "/" : result = num1 / num2;
                break;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("num1", num1);
        params.put("operator", op);
        params.put("num2", num2);
        params.put("result", result);

        return new ModelAndView("calculator",params);
    }
}

