package com.example.corbaclient.corba.huawei;

import com.example.corbaclient.corba.SingletonORB;
import com.example.corbaclient.corba.huawei.HelloApp.Hello;
import com.example.corbaclient.corba.huawei.HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HuaweiClient {

    private final ORB orb;

    @Value("${huawei.corba.host}")
    private String host;

    @Value("${huawei.corba.port}")
    private String port;

    public HuaweiClient() {
        orb = SingletonORB.getInstance().getORB();
    }

    private NamingContextExt getCorbaService(String serviceName) {
        org.omg.CORBA.Object objRef = orb.string_to_object("corbaloc:iiop:" + host + ":" + port + "/" + serviceName);
        return NamingContextExtHelper.narrow(objRef);
    }

    public String getDetailCustomer(String serviceName) {
        try {
            NamingContextExt contextExt = getCorbaService(serviceName);
            Hello href = HelloHelper.narrow(contextExt.resolve_str("HELLO-SERVER"));
            return href.getDetailCustomer();
        } catch (CannotProceed | InvalidName | NotFound cannotProceed) {
            return cannotProceed.toString();
        }
    }


}
