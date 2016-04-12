package se.goteborg.portlets.timtest.controllers;

import com.ibm.portal.MetaData;
import com.ibm.portal.MetaDataProvider;
import com.ibm.portal.ModelException;
import com.ibm.portal.content.ContentMetaDataModel;
import com.ibm.portal.model.ContentMetaDataModelHome;
import com.ibm.portal.model.NavigationSelectionModelHome;
import com.ibm.portal.model.NavigationSelectionModelProvider;
import com.ibm.portal.navigation.NavigationSelectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import se.goteborg.portlets.timtest.services.metaDataService;
import se.goteborg.portlets.timtest.utils.Tools;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("VIEW")
public class MainController {

    @RenderMapping()
    public String viewStart(RenderRequest request, RenderResponse response, Model model) {

        return metaDataService.getMetaData(request, response, model);

    }

}
