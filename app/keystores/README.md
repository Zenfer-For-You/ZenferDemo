// 修改keystore的信息（别乱改）
keytool -genkey -alias yushibao -keyalg RSA -validity 365000 -keystore ysb_employer_keystore.jks
//查询keystore的信息
keytool -v -list -keystore ysb_employer_keystore.jks

Password：ysb123456

Alias：yushibao

MD5：0A:A5:69:C5:79:7F:5B:94:EC:EB:BF:25:32:53:52:3D
SHA1：17:7F:80:13:49:F6:85:AB:21:3E:A9:F7:3E:AF:14:CB:20:57:C2:4E
SHA256：56:83:02:0D:52:0E:1F:1E:99:B9:59:6C:0E:D2:88:C5:BC:23:28:66:8B:AC:CF:9C:CD:BA:49:51:63:10:21:2A

所有者：CN=yushibao, OU=yushibao, O=yushibao, L=yushibao, ST=yushibao, C=yushibao
发布者：CN=yushibao, OU=yushibao, O=yushibao, L=yushibao, ST=yushibao, C=yushibao

