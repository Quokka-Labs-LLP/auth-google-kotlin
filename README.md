# Gmail login module

Features:
Gmail Login and Get User Details

To import this module, First download it.
Now open your project in which you want to use this module.

Goto File -> New -> Import Module
Select the source directory of the Module you want to import and click Finish.
Open Project Structure Dialog (You can open the PSD by selecting File > Project Structure) and from the left panel click on Dependencies.
Open the Dependencies tab.
Click on <All Module> now in right side you will see add dependencies tab.
Click the (+) icon from Dependencies section and click Module Dependency.
Add Module Dependency dialog will open, Now select app module and click Ok.
Then you will see another Add Module Dependency dialog and it will show you gmaillogin module. Click on CheckBox then click ok.
Open your build.gradle file and check that the module is now listed under dependencies as shown below. 
implementation project(path: ':gmaillogin')

After Successfully importing gmaillogin module, Now let's implement it.

Goto this link -> 
https://developers.google.com/identity/sign-in/android/start-integrating

click on Configure a project button
 >Configure a project for Google Sign-in
 >You have to enter your project package name and SHA-1 certificate fingerprint.
 >You can get SHA-1 key by typing this command (gradlew signingreport) in android studio terminal.

now copy client id (Web application client id)
or you can find client id here >  https://console.cloud.google.com/apis/credentials
copy (Web application client id)

Goto res -> values folder and open string.xml file.
Paste these lines and replace client_ID
<string name="client_id">client_ID</string>
Close string.xml file.

Now open AndroidManifest.xml file 
Paste these permission outside the application tag.

<uses-permission android:name="android.permission.INTERNET"/>

Open the Activity in which you want to implement google Login.

implement GoogleLoginListener interface and import all function.

Inside onCreate function.
GmailLogin.initGoogleClient(this,this, getString(R.string.client_id))

Respond to a login result, we need to register a callback.
//here we are passing context, setting up GoogleLoginListener and passing client id.


#override fun onActivityResult is deprecated, new way shown below

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                System.err.println(">>>> inside launcher ")
                GmailLogin.requestForUserdata(result.data)
            }
        }

Add performLogin function when we click on login with google login button
      findViewById<Button>(R.id.button).setOnClickListener { 
        GmailLogin.signIn(launcher)
     }

You will get user data inside onGetProfileSuccess(map: HashMap<String, String?>) function
in the form of HashMap
   
    override fun onGetProfileSuccess(map: HashMap<String, String?>) {
   //for we are getting value in the form of hashMap

        System.err.println(">>>>>>>   on getprofile $map")
        System.err.println(">>>>>>>   " + map["id_token"])
        System.err.println(map["name"])
        System.err.println(map["email"])
        System.err.println(map["gmail_id"])
        if (map["image"] != null) { // in some cases there is no user profile photo.
            System.err.println(map["image"])
        }
    }





