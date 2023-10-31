
实现简易地S_DES算法，功能包括二进制加解密，ASCII码加解密，暴力破解和碰撞测试。

介绍：SAES是一种简化过的数据加密标准，本文介绍如何使用SAES来进行加密以及解密文本数据。

主要功能
1.加密：将明文转换为密文，使用密钥进行加密。
2.解密：将密文转换为明文，使用相同的密钥进行解密。

操作提示：
在进行S-AES的加密解密之前，需要使用一个16bit的二进制密钥。

功能展示：

1.二进制加密

![DPG92E(@DRJ2PETGT2@0_ F](https://github.com/niantian/S_AES-/assets/110915884/e0a903c4-a0df-4ece-8ed6-67b4822e5039)

选择binary，输入明文以及密钥，点击加密按钮，会在下方显示得到的密文。

2二进制解密

![DPG92E(@DRJ2PETGT2@0_ F](https://github.com/niantian/S_AES-/assets/110915884/cfb19422-3236-4893-9512-be12d2dbe4ee)

选择binary，输入密文以及密钥，点击解密按钮，会在下方显示得到的明文。

明文和密文可以进行互解，交叉测试已通过。


![568055319B83813A094B78B172AF5252](https://github.com/niantian/S_AES-/assets/110915884/58b61391-e0c4-4015-9749-edd68c7c8271)

![9CD1F3D49E4EE0D0B52A380D1C432DD3](https://github.com/niantian/S_AES-/assets/110915884/d16b8195-bb3e-4bb5-beb8-9c6fd1a99abd)





3.ASCII码加密

![0S%T5QHM%903){`% H}@28](https://github.com/niantian/S_AES-/assets/110915884/ca2ea267-5abf-4b71-837f-49cc5bb6c4ef)


选择ASCII码，输入任意长度ASCII码的明文，点击加密按钮，在下方显示得到的文本（可能是乱码）

4.ASCII码解密

![E7L_R{H`)`K4ASHWC888~IW](https://github.com/niantian/S_AES-/assets/110915884/a6b872bc-20d4-4b1a-b90e-ba4ed2c06e28)

选择ASCII码，输入ASCII码密文，点击解密按钮，在下方显示得到的文本。

此处也有明密文的互解。


