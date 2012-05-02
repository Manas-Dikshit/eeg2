/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.zcu.kiv.eegdatabase.logic.controller.social;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Controller for processing callback from providers.
 * @Author Michal Patočka
 */
public class OAuthCallbackController extends MultiActionController {

    private Log log = LogFactory.getLog(getClass());
    
   
   
    public ModelAndView oauthcallback(HttpServletRequest request, HttpServletResponse response) { 
        ModelAndView mav = new ModelAndView("social/oauthcallback");
        return mav;




    }
}
