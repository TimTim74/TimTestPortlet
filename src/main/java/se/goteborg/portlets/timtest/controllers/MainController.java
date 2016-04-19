package se.goteborg.portlets.timtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import se.goteborg.portlets.timtest.services.metaDataService;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("VIEW")
public class MainController {

    @RenderMapping()
    public String viewStart(RenderRequest request, RenderResponse response, Model model) {

        return metaDataService.getMetaData(request, response, model);

    }

}
