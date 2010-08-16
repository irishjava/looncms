package uk.mafu.flex.util
{
import mx.managers.BrowserManager;
import mx.managers.IBrowserManager;

public class ContextUtil
{
    public static function getContextPath():String {
        var browserManager:IBrowserManager = BrowserManager.getInstance();
        browserManager.init();
        if (browserManager.url == null) {
            return null;
        }
        else return browserManager.url.toString();
    }

    public static function isLocal():Boolean {
        var browserManager:IBrowserManager = BrowserManager.getInstance();
        browserManager.init();
        var url:String = getContextPath();

        if (url == null)
        {
            return true;

        }
        if (url.indexOf("file:") >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
}