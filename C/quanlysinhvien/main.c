#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//CMS
const MAX=100;
void showmenu(void);
void addnewstudent(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int *size);
void printlist(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size);
int findindex(char id[][MAX],int size,char key);
void printstudent(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int pos);
void delbypos(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int *size,int pos);
void highest(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size);
void fnameF(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size);

int main()
{
    char id[MAX][MAX];
    char fname[MAX][MAX];
    char lname[MAX][MAX];
    int yob[MAX];
    float gpa[MAX];
    int size=0;
    int choice;
    char buffer;
    do{
        showmenu();
        do{
        printf("\ninput your choice(integer 1-7):");
        scanf("%d",&choice);
        scanf("%c",&buffer);
        fflush(stdin);
        if(buffer!=10){
            printf("\ndcmm nhap lai di");
        }
    }while(buffer!=10);

        switch(choice){
        case 1:{
            printf("\nadd new student");
            addnewstudent(id,fname,lname,yob,gpa,&size);
            break;
        }
        case 2:{
            printf("\nsearching student by id");
            printf("\nnhap id:");
            char id[100];
            gets(id);
            int pos=findindex(id,size,id);
            if(pos==-1){
                printf("\n student does not exist");
            }else{
                printstudent(id,fname,lname,yob,gpa,pos);
            }
            break;
        }
        case 3:{
            printf("\ndelete student");
             printf("\nnhap id:");
            char id[100];
            gets(id);
            int pos=findindex(id,size,id);
            if(pos==-1){
                printf("\n student does not exit");
            }else{
                delbypos(id,fname,lname,yob,gpa,&size,pos);
            }
            break;
        }
        case 4:{
            printf("\nhighset gpa");
            highest(id,fname,lname,yob,gpa,size);
            break;
        }
        case 5:{
            printf("\nprint student list");
            printlist(id,fname,lname,yob,gpa,size);
            break;
        }
        case 6:{
            printf("\nsort student by fname");
            fnameF(id,fname,lname,yob,gpa,size);
            printlist(id,fname,lname,yob,gpa,size);
            break;
        }
        case 7:{
            printf("\nsee you again");
            return;
        }
        default:{
            printf("\nchoice is required between 1 and 7!!!");
            break;
        }

            }
    }while(choice!=7);
    return 0;
}
void showmenu(void){

    printf("\nstudent management aplication");
    printf("\n1/insert new student into student list");
    printf("\n2/search student by id");
    printf("\n3/delete student by id");
    printf("\n4/print highest gpa student list");
    printf("\n5/print student list");
    printf("\n6/sort student list oder by fname ascending");
    printf("\n7/quit");
}
void addnewstudent(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int *size){
    int isfind;
    do{
    printf("\nid: ");
    char key[100];
    gets(key);
    fflush(stdin);
    isfind=findindex(id,*size,key);
    if(isfind!=-1){
        printf("\nthis id has been used");
    }else{
    strcpy(id[*size],key);
    }
}while(isfind!=-1);
//printf("\ninput id:");
//gets(id[*size]);
//fflush(stdin);
printf("\ninput fname:");
gets(fname[*size]);
fflush(stdin);
printf("\ninput lname:");
gets(lname[*size]);
fflush(stdin);
printf("\ninput yob:");
scanf("%d",&yob[*size]);
fflush(stdin);
printf("\ninput gpa:");
scanf("%f",&gpa[*size]);
fflush(stdin);
(*size)++;
printf("\ninsertion is successfull");

}
void printlist(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size){
if(size==0){
    printf("\nNothing to print");
}else{
for(int i=0;i<=size-1;i++){
    printf("\n%-8s|%-10s|%-10s|%-4d|%5.2f",id[i],fname[i],lname[i],yob[i],gpa[i]);
}
}
}
int findindex(char id[][MAX],int size,char key){
//char inpid[MAX];
//printf("\ninput id:");
//gets(inpid);
for(int i=0;i<=size-1;i++){
    if(strcmp(id[i],key)==0) return i;
}
return -1;
}
void printstudent(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int pos){

    printf("\n%-8s|%-10s|%-10s|%-4d|%5.2f",id[pos],fname[pos],lname[pos],yob[pos],gpa[pos]);
}
void delbypos(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int *size,int pos){
for(int i=pos;i<=*size-1;i++){
    strcpy(id[i],id[i+1]);
    strcpy(fname[i],fname[i+1]);
    strcpy(lname[i],lname[i+1]);
    yob[i]=yob[i+1];
    gpa[i]=gpa[i+1];
}
(*size)--;
}
void highest(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size){
    if(size==0){
        printf("\nhave no student");
        return;
    }
float maxgpa=gpa[0];
for(int i=0;i<=size-1;i++){
    if(gpa[i]>maxgpa)maxgpa=gpa[i];
}
for(int i=0;i<=size-1;i++){
    if(gpa[i]==maxgpa){
        printstudent(id,fname,lname,yob,gpa,i);
    }
}
}
void fnameF(char id[][MAX],char fname[][100],char lname[][100],int yob[],float gpa[],int size){
for(int i=0;i<=size-1;i++){
    for(int j=i+1;j<=size-1;j++){
        if(strcmp(fname[i],fname[j])==1){
            char tmp[100];
            strcpy(tmp,id[i]);
            strcpy(id[i],id[j]);
            strcpy(id[j],tmp);

            strcpy(tmp,fname[i]);
            strcpy(fname[i],fname[j]);
            strcpy(fname[j],tmp);

            strcpy(tmp,lname[i]);
            strcpy(lname[i],lname[j]);
            strcpy(lname[j],tmp);

            int  tmps=yob[i];
            yob[i]=yob[j];
            yob[j]=tmps;

            float tmpf=gpa[i];
            gpa[i]=gpa[j];
            gpa[j]=tmpf;
        }
    }
}
}
