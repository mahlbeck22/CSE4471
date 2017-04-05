# CSE4471

* Activity files: [CSE4471/tree/master/app/src/main/java/com/example/ahlbe/cse4471](https://github.com/mahlbeck22/CSE4471/tree/master/app/src/main/java/com/example/ahlbe/cse4471)
* XML GUI files: [CSE4471/tree/master/app/src/main/res/layout](https://github.com/mahlbeck22/CSE4471/tree/master/app/src/main/res/layout)

## Encryption class
This project uses the Encryption class from https://github.com/simbiose/Encryption

To encrypt:
```
String key = "Key";
String salt = "Salt";
byte[] iv = new byte[16];
Encryption encryption = Encryption.getDefault(key, salt, iv);
String encrypted = encryption.encryptOrNull("Text to encrypt");
```

To decrypt:
```
String decrypted = encryption.decryptOrNull(encrypted);
```
