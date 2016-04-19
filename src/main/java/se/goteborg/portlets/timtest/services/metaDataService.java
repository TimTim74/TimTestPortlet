package se.goteborg.portlets.timtest.services;

import com.ibm.portal.MetaData;
import com.ibm.portal.MetaDataProvider;
import com.ibm.portal.ModelException;
import com.ibm.portal.content.ContentMetaDataModel;
import com.ibm.portal.model.ContentMetaDataModelHome;
import com.ibm.portal.model.NavigationSelectionModelHome;
import com.ibm.portal.model.NavigationSelectionModelProvider;
import com.ibm.portal.navigation.NavigationSelectionModel;
import org.springframework.ui.Model;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class metaDataService {
    private static final Logger logger = Logger.getLogger(metaDataService.class.getName());

    public static String getMetaData(RenderRequest request, RenderResponse response, Model model) {
        HttpServletRequest httpServletRequest =  com.ibm.ws.portletcontainer.portlet.PortletUtils.getHttpServletRequest(request);
        HttpServletResponse httpServletResponse = com.ibm.ws.portletcontainer.portlet.PortletUtils.getHttpServletResponse(response);

        try {
            Context ctx = new InitialContext();

            NavigationSelectionModelHome navigationSelectionModelHome = (NavigationSelectionModelHome) ctx.lookup(NavigationSelectionModelHome.JNDI_NAME);
            NavigationSelectionModelProvider nsmProvider = navigationSelectionModelHome.getNavigationSelectionModelProvider();
            NavigationSelectionModel nsm = nsmProvider.getNavigationSelectionModel(httpServletRequest, httpServletResponse);

            ContentMetaDataModelHome contentMetaDataModelHome = (ContentMetaDataModelHome) ctx.lookup(ContentMetaDataModelHome.JNDI_NAME);
            ContentMetaDataModel contentMetaDataModel = contentMetaDataModelHome.getContentMetaDataModelProvider().getContentMetaDataModel(httpServletRequest, httpServletResponse);
            MetaData metaDataAggr = contentMetaDataModel.getMetaData(nsm.getSelectedNode());

            Iterator iterator = metaDataAggr.iterator();
            Map allMetaDataAggr = new HashMap();
            Map allMetaDataNonAggr = new HashMap();

            Tools.putMetaInMap(iterator, allMetaDataAggr, null);

            if (nsm.getSelectedNode() instanceof MetaDataProvider) {
                MetaData metaDataNonAggr = ((MetaDataProvider) nsm.getSelectedNode()).getMetaData();
                Iterator itNonAggr = metaDataNonAggr.iterator();
                Tools.putMetaInMap(itNonAggr, allMetaDataNonAggr, null);
            }


            model.addAttribute("allMetaDataAggr", allMetaDataAggr);
            model.addAttribute("allMetaDataNonAggr", allMetaDataNonAggr);

            String paramHidden = (String) metaDataAggr.getValue("com.ibm.portal.Hidden");
            model.addAttribute("paramHidden", paramHidden);

        } catch (NamingException e) {
            logger.log(Level.SEVERE, "NamingException error occured in method getMetaData()", e);

        } catch (ModelException e) {
            logger.log(Level.SEVERE, "ModelException error occured in method getMetaData()", e);
        }

        return "view";
    }
}
