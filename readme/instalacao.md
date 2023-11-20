---
description: Para realizar a instalação da API use o maven ou gradle
---

# Instalação

## Usando o maven

{% hint style="warning" %}
Substitua o REPLACE\_VERSION pela versão que desejar
{% endhint %}

```xml
<repositories>
     <repository>
         <id>jitpack.io</id>
         <url>https://jitpack.io</url>
      </repository>
</repositories>
<dependencies>
    <dependency>
	<groupId>com.github.Jhonatas48</groupId>
	<artifactId>mysql_api_simples</artifactId>
	<version>REPLACE_VERSION</version>
     </dependency>
<dependencies>
```

## Usando Gradle

### Adicione-o em seu build root.gradle no final dos repositórios

```gradle
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

### Adicionar a dependência

{% hint style="warning" %}
Substitua o REPLACE\_VERSION pela versão que desejar
{% endhint %}

```gradle
dependencies {
	        implementation 'com.github.Jhonatas48:mysql_api_simples:REPLACE_VERSION'
	}
```
