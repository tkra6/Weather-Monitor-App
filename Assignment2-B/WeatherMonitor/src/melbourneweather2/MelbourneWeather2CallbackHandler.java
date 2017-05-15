
/**
 * MelbourneWeather2CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package melbourneweather2;

    /**
     *  MelbourneWeather2CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MelbourneWeather2CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MelbourneWeather2CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MelbourneWeather2CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getRainfall method
            * override this method for handling normal response from getRainfall operation
            */
           public void receiveResultgetRainfall(
                    melbourneweather2.MelbourneWeather2Stub.GetRainfallResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRainfall operation
           */
            public void receiveErrorgetRainfall(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLocations method
            * override this method for handling normal response from getLocations operation
            */
           public void receiveResultgetLocations(
                    melbourneweather2.MelbourneWeather2Stub.GetLocationsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLocations operation
           */
            public void receiveErrorgetLocations(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTemperature method
            * override this method for handling normal response from getTemperature operation
            */
           public void receiveResultgetTemperature(
                    melbourneweather2.MelbourneWeather2Stub.GetTemperatureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTemperature operation
           */
            public void receiveErrorgetTemperature(java.lang.Exception e) {
            }
                


    }
    