import urllib2, urllib
mydata=[('one','https://files.000webhost.com/images/.jpg')]    #The first is the var name the second is the value
mydata=urllib.urlencode(mydata)
path='https://files.000webhost.com/ocr.php'    #the url you want to POST to
req=urllib2.Request(path, mydata)
req.add_header("Content-type", "application/x-www-form-urlencoded")
page=urllib2.urlopen(req).read()
print page