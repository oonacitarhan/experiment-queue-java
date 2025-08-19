from pathlib import Path
import os,subprocess
root=Path(__file__).resolve().parents[1]
java=os.environ.get('JAVA','java')
sources=sorted(str(p) for p in (root/'src').rglob('*.java'))
(root/'build/classes').mkdir(parents=True,exist_ok=True)
subprocess.run([java,str(root/'scripts/Compile.java'),'--release','17','-Xlint:all','-d',str(root/'build/classes'),*sources],check=True)
