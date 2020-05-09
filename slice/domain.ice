// Custom package mapping
["java:package:cl.ucn.disc.pdis.lab.zeroice"]
module model
{
    // The API
    interface Engine
    {
        /**
        *@return the date
        */
        string getDate();

        /**
        * Verification RUT with/without underscore.
        * @return DV , void if RUT is incorrect.
        */
        string getCheckDigit(string rut);
    }

}
