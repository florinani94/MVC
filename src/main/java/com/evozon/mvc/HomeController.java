package com.evozon.mvc;

import com.evozon.service.ProductService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


@Controller
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String homePage() {
        return "redirect:/products";
    }

}
