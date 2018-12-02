# Arc
**LatestVersion**

arc library and demo


Scan QRCode to download demo application below:

![](/output/arc_demo_qr_code.png)

### 1、implementation
+ 1.1、Gradle
```
compile 'jsc.kit.arc:arc-component:_latestVersion'
```
+ 1.2、Maven
```
<dependency>
  <groupId>jsc.kit.arc</groupId>
  <artifactId>arc-component</artifactId>
  <version>_latestVersion</version>
  <type>pom</type>
</dependency>
```

### 2、attrs
+ 2.1、[ArcView](/arcLibrary/src/main/java/jsc/kit/arc/ArcView.java)

| 名称 | 类型 | 描述 |
|:---|:---|:---|
|`arcDrawable`|reference|图片资源文件|
|`arcHeight`|dimension|弧形高度|
|`arcDirection`|enum|弧形朝向|

| 弧形朝向枚举 | int值 | 描述 |
|:---|:---|:---|
|`leftOutside`|0|左凸出|
|`leftInside`|1|左凹进|
|`topOutside`|2|上凸出|
|`topInside`|3|上凹进|
|`rightOutside`|4|右凸出|
|`rightInside`|5|右凹进|
|`bottomOutside`|6|下凸出|
|`bottomInside`|7|下凹进|
|`leftRightOutside`|8|左右凸出|
|`leftRightInside`|9|左右凹进|
|`topBottomOutside`|10|上下凸出|
|`topBottomInside`|11|上下凹进|

### 3、usage
| 组件 | 使用示例 |
|:---|:---|
|[ArcDrawable](/arcLibrary/src/main/java/jsc/kit/arc/ArcDrawable.java)|[ArcDrawableFragment](/app/src/main/java/jsc/exam/com/arc/fragments/ArcDrawableFragment.java)|
|[ArcView](/arcLibrary/src/main/java/jsc/kit/arc/ArcView.java)|[ArcViewFragment](/app/src/main/java/jsc/exam/com/arc/fragments/ArcViewFragment.java)|

### 4、Screenshots
+ 4.1、[ArcDrawable](/arcLibrary/src/main/java/jsc/kit/arc/ArcDrawable.java)

+ 4.2、[ArcView](/arcLibrary/src/main/java/jsc/kit/arc/ArcView.java)

### 5、release log

### LICENSE
```
   Copyright 2018 JustinRoom

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
