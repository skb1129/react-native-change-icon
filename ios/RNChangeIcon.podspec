
Pod::Spec.new do |s|
  s.name         = "RNChangeIcon"
  s.version      = "1.0.0"
  s.summary      = "RNChangeIcon"
  s.description  = <<-DESC
                  RNChangeIcon
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNChangeIcon.git", :tag => "master" }
  s.source_files  = "RNChangeIcon/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  