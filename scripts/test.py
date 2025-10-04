from pathlib import Path
import os,subprocess
root=Path(__file__).resolve().parents[1];java=os.environ.get('JAVA','java')
for name in ['ReferenceTest','FeatureTest']:
    subprocess.run([java,'-cp',str(root/'build/classes'),'lab.'+name],cwd=root,check=True)
result=subprocess.run([java,'-cp',str(root/'build/classes'),'lab.Main','--invalid'],cwd=root,capture_output=True)
assert result.returncode==2
print('CLI invalid-input check passed')
