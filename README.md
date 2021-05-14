# TestWebViewApp
Application with build-in web browser.

WebViewApp is simple single activity application, that allows to serf the Internet using WebView. WebView is located in fragment and takes all its size.
App has two fragments. First is created in order to show loading screen while response is arriving.
Second makes request to the server after response was arrived and set link from response to the webView depending on its first run or not.

![FirstLoading](https://user-images.githubusercontent.com/69154266/118257187-01f85980-b4b7-11eb-8b0e-b7f4008836f0.gif)

Completed additional tasks: 
1. WebView saves user history and pressing back return user on previous page.

![Back](https://user-images.githubusercontent.com/69154266/118257213-09b7fe00-b4b7-11eb-98ce-1e588790e88d.gif)


2. While app makes first request to the server, response are saving in application using sharedPreferences. It allows to use links from response without making another request.
3. App runs in fullscreen mode.
4. Changing portrait and landscape mode without restarting webView and saving state.

![Change mode](https://user-images.githubusercontent.com/69154266/118257248-163c5680-b4b7-11eb-8d56-90c3ad4dac2e.gif)

5. Custom application icon has been set.

![icon](https://user-images.githubusercontent.com/69154266/118257410-484db880-b4b7-11eb-93eb-9e8e24b6c439.jpg)
