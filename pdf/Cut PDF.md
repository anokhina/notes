## Cut PDF

```
sudo apt install mupdf
sudo apt install mupdf-tools
```

The example below will chop each page of input.pdf into 3 horizontal and 8 vertical parts (thus creating 24 pages of output for each 1 of input):

```
mutool poster -x 3 -y 8 input.pdf output.pdf
```

Rotate pages pdf180 (from pdfjam)

Reorder pages

```
mutool clean input.pdf output.pdf 2,1,3,4,6,5,7-10 
```

where 2,1,3,4,6,5 is the new ordering 

Extract images:

```
mutool extract file.pdf
```

## PDF crop

Doesn't work with jpeg pages.

```
sudo apt-get install texlive-extra-utils

pdfcrop input.pdf output.pdf
pdfcrop --margins 5 input.pdf output.pdf
```

Gui app

```
sudo snap install briss
```
Briss doesn't run. But copy all jar from /snap/briss/3/briss/, remove it 

```
sudo snap remove briss
```
and run

```
java -jar briss-0.9.jar -s in.pdf -d out.pdf
```

### Cut with briss-0.9

Download, unzip and run

<https://sourceforge.net/projects/briss/files/release%200.9/>

```
java -jar bris-09.jar
```

## Images to pdf


In case 

```
convert-im6.q16: memory allocation failed `156.gif' @ error/gif.c/ReadGIFImage/1303.
```

edit ```sudo gvim /etc/ImageMagick-6/policy.xml```

Increase memory

```xml
  <policy domain="resource" name="memory" value="8GiB"/>
```

Give rights to read write pdf

```xml
 <policy domain="coder" rights="read|write" pattern="PDF" />
```

```
convert *.gif out.pdf
```

### img2pdf

```
img2pdf --pagesize a4 -o ../out.pdf *.jpeg
```

### gthumb

gthumb -- Инструменты -- Преобразовать формат

## Decrease size

from <https://askubuntu.com/a/256449>


```
gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/ebook -dNOPAUSE -dQUIET -dBATCH -sOutputFile=output.pdf input.pdf
```


*    ```-dPDFSETTINGS=/screen``` lower quality, smaller size. (72 dpi)
*    ```-dPDFSETTINGS=/ebook``` for better quality, but slightly larger pdfs. (150 dpi)
*    ```-dPDFSETTINGS=/prepress``` output similar to Acrobat Distiller "Prepress Optimized" setting (300 dpi)
*    ```-dPDFSETTINGS=/printer``` selects output similar to the Acrobat Distiller "Print Optimized" setting (300 dpi)
*    ```-dPDFSETTINGS=/default``` selects output intended to be useful across a wide variety of uses, possibly at the expense of a larger output file
