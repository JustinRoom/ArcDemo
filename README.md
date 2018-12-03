# Arc
**LatestVersion**

[ ![Download](https://api.bintray.com/packages/justinquote/maven/arc-component/images/download.svg) ](https://bintray.com/justinquote/maven/arc-component/_latestVersion)

<a href='https://bintray.com/justinquote/maven/arc-component?source=watch' alt='Get automatic notifications about new "arc-component" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

arc library and demo


Scan QRCode to download demo application below:

![](/app/src/main/res/drawable/arc_demo_qr_code.png)

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
|`arcDirection`|enum|弧形朝向|

|`arcDirection`枚举 | int值 | 描述 |
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
|`arcShader`|string|资源类型|

|`arcShader`枚举 | 格式 | 描述 |
|:---|:---|:---|
|`drawable`|`drawable`、`arcDrawable`|drawable下的图片资源|
|`assets`|`assets, img/2.jpg`|assets下的图片资源|
|`linear`|`linear, #FF3A80, #FF3745`|LinearGradient参数|
|`sweep`|`sweep, #FF3A80, #FF3745`|SweepGradient参数|
|`radial`|`radial, #FF3A80, #FF3745`|RadialGradient参数|

### 3、usage
+ drawable下的图片资源
```
        <jsc.kit.arc.ArcView
            android:id="@+id/arc_view1"
            android:layout_width="100dp"
            android:layout_height="240dp"
            android:layout_gravity="center_horizontal"
            app:arcDirection="topBottomOutside"
            app:arcDrawable="@drawable/head"
            app:arcHeight="@dimen/space_24"
            app:arcShader="drawable" />
```
+ assets下的图片资源
```
        <jsc.kit.arc.ArcView
            android:id="@+id/arc_view2"
            android:layout_width="100dp"
            android:layout_height="240dp"
            android:layout_gravity="center_horizontal"
            android:layout_marginLeft="@dimen/space_12"
            app:arcDirection="topBottomOutside"
            app:arcDrawable="@drawable/head"
            app:arcHeight="@dimen/space_24"
            app:arcShader="radial, #D81B60, #008577" />
```
+ Gradient参数
```
        <jsc.kit.arc.ArcView
            android:id="@+id/arc_view3"
            android:layout_width="100dp"
            android:layout_height="240dp"
            android:layout_gravity="center_horizontal"
            android:layout_marginLeft="@dimen/space_12"
            app:arcDirection="topBottomOutside"
            app:arcDrawable="@drawable/head"
            app:arcHeight="@dimen/space_24"
            app:arcShader="assets, img/2.jpg" />
```

| 组件 | 使用示例 |
|:---|:---|
|[ArcDrawable](/arcLibrary/src/main/java/jsc/kit/arc/ArcDrawable.java)|[ArcDrawableFragment](/app/src/main/java/jsc/exam/com/arc/fragments/ArcDrawableFragment.java)|
|[ArcView](/arcLibrary/src/main/java/jsc/kit/arc/ArcView.java)|[ArcViewFragment](/app/src/main/java/jsc/exam/com/arc/fragments/ArcViewFragment.java)|

### 4、Screenshots
+ 4.1、[ArcDrawable](/arcLibrary/src/main/java/jsc/kit/arc/ArcDrawable.java)
![ArcDrawable](/output/shots/arc_drawable_s.png)

+ 4.2、[ArcView](/arcLibrary/src/main/java/jsc/kit/arc/ArcView.java)
![ArcView](/output/shots/arc_view_s.png)

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
