![K{1ZWP4WM$6YX2$5R`Q%6AY](https://github.com/niantian/S_AES-/assets/110915884/17137c91-8874-4e0b-9691-1ad9965902cc)
实现S_AES算法，功能包括二进制加解密，ASCII码加解密，暴力破解和碰撞测试。

介绍：SAES是一种简化过的数据加密标准，本文介绍如何使用SAES来进行加密以及解密文本数据。

主要功能
1.加密：将明文转换为密文，使用密钥进行加密。
2.解密：将密文转换为明文，使用相同的密钥进行解密。


操作提示：
在进行S-AES的加密解密之前，需要使用一个16bit的二进制密钥。

功能展示：

1.二进制加密

![_`5F `0}XOCYT GFRY ~}%J](https://github.com/niantian/S_AES-/assets/110915884/8e5c17ef-576f-4a2b-bc43-56751543af3d)



选择二进制，输入明文以及密钥，点击加密按钮，会在下方显示得到的密文。

2二进制解密

![Q`QO4$A{O71}EUJ{O %`AOV](https://github.com/niantian/S_AES-/assets/110915884/a8be85b0-7550-4e2a-8a07-6de12b166d42)


选择二进制，输入密文以及密钥，点击解密按钮，会在下方显示得到的明文。

明文和密文可以进行互解，交叉测试已通过。


![568055319B83813A094B78B172AF5252](https://github.com/niantian/S_AES-/assets/110915884/58b61391-e0c4-4015-9749-edd68c7c8271)

![9CD1F3D49E4EE0D0B52A380D1C432DD3](https://github.com/niantian/S_AES-/assets/110915884/d16b8195-bb3e-4bb5-beb8-9c6fd1a99abd)


3.二重加解密：


![Q`QO4$A{O71}EUJ{O %`AOV](https://github.com/niantian/S_AES-/assets/110915884/b156df50-54ef-4b4d-a55b-5fcdbbcc7edb)

![GBB8H@ 6Q9 T8~WKY @OL5](https://github.com/niantian/S_AES-/assets/110915884/8575f200-8d19-4f92-96dd-1a59367de158)

在下拉栏里选择二重，输入32bit的密钥，其他操作与加解密相同。

4.三重加解密（K1+K2+K3）；

![20LBAXPMB0R~1SQN6}K8Y5X](https://github.com/niantian/S_AES-/assets/110915884/215dc6c0-331c-4279-82df-3494d8f61720)

![7)C5Q 157R@X)7L3%X 8T@F](https://github.com/niantian/S_AES-/assets/110915884/492d0a84-95d6-4020-a8f8-ed9058b3ed07)


5.ASCII码加密

![K{1ZWP4WM$6YX2$5R`Q%6AY](https://github.com/niantian/S_AES-/assets/110915884/1252b948-3dff-4be5-94ac-642934cfca16)

![N}@$90ZEVL6Y%ABYX7R@_G0](https://github.com/niantian/S_AES-/assets/110915884/a6a2b391-f2ce-4710-9368-61d64a7e7ffb)

![}}GAN5WWRW1JGZT2~Y2)~Z3](https://github.com/niantian/S_AES-/assets/110915884/2d5c6e17-4632-4c0e-aa2f-bfff95df9352)


选择ASCII码，输入任意长度ASCII码的明文，点击加密按钮，在下方显示得到的文本（可能是乱码），二重、三重加解密也都支持

6.ASCII码解密


![E}OVN7B64V0P8%FHSF O9_3](https://github.com/niantian/S_AES-/assets/110915884/4d6c8bb9-8f07-4ad8-8c40-5505085f545b)


![3)38V MV@TNLCVU1PP V WL](https://github.com/niantian/S_AES-/assets/110915884/1afd25f9-b351-421c-99a7-0195ae52729b)

![7C4P S M%_Z9ZTKGL1FW4CD](https://github.com/niantian/S_AES-/assets/110915884/8042399d-ab9b-4df8-825d-2ce9093ac731)


选择ASCII码，输入ASCII码密文，点击解密按钮，在下方显示得到的文本。二重与三重加解密也都支持。

此处也有明密文的互解。


7.中间相遇攻击

运行MID_Attack.java,观察终端输出的结果：
测试明密文对为：“0111011100011100” "1010101110101100"

![2KWIFRTO@~N 4JE011NLB)B](https://github.com/niantian/S_AES-/assets/110915884/fe6f073b-83aa-444c-824a-7778a7dbae77)

8.cbc测试

运行SAEScbc.java，观察终端输入的结果：

![image](https://github.com/niantian/S_AES-/assets/110915884/44310f16-6f65-4595-85a2-ee570636e40e)

当密钥和常量相同时，修改明密文对得到的结果与原始结果明显不同。


